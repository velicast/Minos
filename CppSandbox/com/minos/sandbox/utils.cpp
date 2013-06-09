/* 
 * File:   utils.cpp
 * Author: Eduar Castrillo (eduarc)
 *
 * Created on April 20, 2013, 5:33 AM
 */
#include "utils.h"

#include <cstring>
#include <cstdlib>
#include <cstdio>
#include <sys/syscall.h>
#include <sys/ptrace.h>
#include <sys/fcntl.h>
#include <sys/user.h>
#include <unistd.h>
#include <iostream>

int read_memory(int pid) {
  
  char buff[128];
  
  sprintf(buff, "/proc/%d/status", pid);
  
  FILE *f = fopen(buff, "r");
  if (f == NULL) {
    // LOG here!
    perror("fopen");
    return -1;
  }
  
  int VmPeak = 0, VmSize = 0, VmExe = 0, VmLib = 0, VmStk = 0;
  while (fgets(buff, 128, f)) {
    if (!strncmp(buff, "VmPeak:", 7))
      sscanf(buff + 7, "%d", &VmPeak);
    else if (!strncmp(buff, "VmSize:", 7))
      sscanf(buff + 7, "%d", &VmSize);
    else if (!strncmp(buff, "VmStk:", 6))
      sscanf(buff + 6, "%d", &VmStk);
    else if (!strncmp(buff, "VmExe:", 6))
      sscanf(buff + 6, "%d", &VmExe);
    else if (!strncmp(buff, "VmLib:", 6))
      sscanf(buff + 6, "%d", &VmLib);
  }
  fclose(f);
  
  if (VmPeak) VmSize = VmPeak;
  int cons = VmSize - VmExe - VmLib - VmStk;
  return cons;
}

int read_time(int pid) {
  
  char buff[128];
  
  sprintf(buff, "/proc/%d/stat", pid);
  
  FILE *f = fopen(buff, "r");
  
  if (f == NULL) {
    // LOG here!
    perror("fopen");
    return -1;
  }
  
  int ut, st;
  while (fgetc(f) != ')');
  fgetc(f);
  fscanf(f, "%*c %*d %*d %*d %*d %*d %*d %*u "
         "%*u %*u %*u %d %d ", &ut, &st);
  fclose(f);
  
  return (ut + st + 0.) / sysconf(_SC_CLK_TCK) * 1000;
}

/* Lee un string desde la memoria de un proceso vigilado */
int read_string(int pid, unsigned addr, char *buff,unsigned max_len) {
  
  for (unsigned i = 0; i < max_len; i += sizeof(unsigned)) {
    long data = ptrace(PTRACE_PEEKDATA, pid, i + addr, 0);
    if (data == -1) {
      // LOG here
      perror("ptrace");
      return -1;
    }
    char *p = (char *)&data;
    for (unsigned j = 0; j < sizeof(unsigned); ++j, ++p) {
      if (!isprint(*p) || i + j > max_len) {
        buff[i + j] = 0;
        return 0;
      }
      buff[i + j] = *p;
    }
  }
  buff[max_len] = 0;
  return 0;
}

int set_limit(int resource, rlim_t limit) {
  
  rlimit r;
  r.rlim_cur = limit;
  r.rlim_max = limit + 1;
  if (setrlimit(resource, &r) == -1) {
    // LOG here!
    perror("setrlimit");
    return -1;
  }
	return 0;
}

int create_process(process_t &target_process) {
    
  int pid = fork();
  
  if (pid == -1) {
    // LOG here!
    perror("fork");
    return -1;
  }
  if (pid > 0) {
    return pid;
  }
    // from child
  const char **file = target_process.file;
  int fd[3] = {};
  int mode[] = {O_RDONLY, O_WRONLY | O_CREAT | O_TRUNC, O_WRONLY};
	
  for (int i = 0; i < 3; ++i) {
    if (file[i]) {
      fd[i] = open(file[i], mode[i], 0777);
    }
    if (fd[i] == -1) {
      // LOG here!
      exit(-1);
    }
    if (fd[i]) {
      if(dup2(fd[i], i) == -1) return -1;
      close(fd[i]);
    }
  }
  for (int i = 3; i < 100; ++i) close(i);
	
  if (set_limit(RLIMIT_AS, target_process.memory_limit * 1024) == -1) {
    // LOG here!
    perror("setrlimit");
    exit(-1);
  }
  if (set_limit(RLIMIT_DATA, target_process.memory_limit * 1024) == -1) {
    // LOG here!
    perror("setrlimit");
    exit(-1);
  }
  if (set_limit(RLIMIT_CPU, target_process.time_limit) == -1) {
    // LOG here!
    perror("setrlimit");
    exit(-1);
  }
  if (set_limit(RLIMIT_STACK, target_process.stack_limit * 1024) == -1) {
    // LOG here!
    perror("setrlimit");
    exit(-1);
  }
  if (set_limit(RLIMIT_NPROC, 0) == -1) {
    // LOG here!
    perror("setrlimit");
    exit(-1);
  }
  if (set_limit(RLIMIT_FSIZE, target_process.output_limit * 1024) == -1) {
    // LOG here!
    perror("setrlimit");
    exit(-1);
  }
  if (ptrace(PTRACE_TRACEME, 0, 0, 0) == -1) {
    // LOG here!
    perror("ptrace");
    exit(-1);
  }
  if (execv(target_process.exec, 0) == -1) {
    // LOG here!
    perror("execv");
    exit(-1);
  }
  exit(-1);
}