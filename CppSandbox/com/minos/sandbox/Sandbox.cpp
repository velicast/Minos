/* 
 * File:   Sandbox.cpp
 * Author: Eduar Castrillo (eduarc)
 * 
 * Created on April 20, 2013, 5:34 AM
 */

#include <cstdio>
#include <cstdlib>
#include <cstring>

#include <sys/syscall.h>
#include <sys/ptrace.h>
#include <sys/fcntl.h>
#include <sys/user.h>
#include <sys/wait.h>

#include "Sandbox.h"
#include "syscalls.h"
#include "SandboxReply.h"
#include "utils.h"

int Sandbox::memory_used  = 0;
int Sandbox::time_elapsed = 0;

void Sandbox::update_statistics(int pid) {
  
  int m = read_memory(pid);
	int t = read_time(pid);
  if (m > Sandbox::memory_used) {
    memory_used = m;
  }
  if (t > Sandbox::time_elapsed) {
    time_elapsed = t;
  }
}

bool Sandbox::allowed_to_open(char *path, int mode) {
	
  if ((mode & O_WRONLY) == O_WRONLY ||
      (mode & O_RDWR)   == O_RDWR   ||
      (mode & O_CREAT)  == O_CREAT  ||
      (mode & O_APPEND) == O_APPEND) {
    // LOG here!
    return false;
  }
  if (!strlen(path)) {
    // LOG here!
    return false;
  }
  if (path[0] == '.' || path[0] != '/') {
    // LOG here!
    return false;
  }
  if (strncmp(path, "/etc/", 5) == 0
    || strncmp(path, "/usr/lib/", 9) == 0) {
    return true;
  }
    // LOG here!
  return false;
}

int Sandbox::trace(int pid, int time_limit, int memory_limit) {
  
  bool before_syscall = true, first_execve = false;
  long long requested_brk = 0;
  int status;
	
  while (waitpid(pid, &status, 0) == pid) {
      // execution finished
    if (!WIFSTOPPED(status)) {
      break;
    }
		// update statistics
    update_statistics(pid);
		
    if (Sandbox::time_elapsed > time_limit) {
        Sandbox::time_elapsed =  time_limit + 1;
      ptrace(PTRACE_KILL, pid, 0, 0);
      return SandboxReply::TIME_LIMIT_EXCEEDED;
    }
    if (Sandbox::memory_used > memory_limit) {
        Sandbox::memory_used = memory_limit + 1;
      ptrace(PTRACE_KILL, pid, 0, 0);
      return SandboxReply::MEMORY_LIMIT_EXCEEDED;
    }
      // get stop signal
    int sig = WSTOPSIG(status);
      // is not a sigtrap generated because tracing,
      // deliver signal to child
    if (sig != SIGTRAP) {
      ptrace(PTRACE_SYSCALL, pid, 0, sig);
      continue;
    }
      // ----------------------
      // inspect Syscall Traced
      // ----------------------
    user_regs_struct regs;
		
    if (ptrace(PTRACE_GETREGS, pid, 0, &regs) == -1) {
      // LOG here!
      return SandboxReply::INTERNAL_ERROR;
    }
		
      // Skip first SYS_execve call
    if (regs.REG_SYSCALL == SYS_execve && !first_execve) {
      first_execve = true;
      ptrace(PTRACE_SYSCALL, pid, 0, 0);
      continue;
    }
    else if (regs.REG_SYSCALL == SYS_open) {
      if (before_syscall) {
        static char path[1024];
        if (read_string(pid, regs.REG_ARG0, path, 1024) == -1) {
          ptrace(PTRACE_KILL, pid, 0, 0);
          return SandboxReply::INTERNAL_ERROR;
        }
        if (!allowed_to_open(path, regs.REG_ARG1)) {
          ptrace(PTRACE_KILL, pid, 0, 0);
          return SandboxReply::ILLEGAL_INSTRUCTION;
        }
        before_syscall = false;
      } else {
        before_syscall = true;
      }
    }
      // Test memory request
    else if (regs.REG_SYSCALL == SYS_brk) {
      if (before_syscall) {
        requested_brk = regs.REG_ARG0;
        before_syscall = false;
      } else {
        if (regs.REG_ARG0 < requested_brk) {
            // LOG here!
          ptrace(PTRACE_KILL, pid, 0, 0);
          Sandbox::memory_used = memory_limit + 1;
          return SandboxReply::MEMORY_LIMIT_EXCEEDED;
        }
        before_syscall = true;
      }
    }
      // Check exit code. Only 0 value is allowed.
    else if (regs.REG_SYSCALL == SYS_exit_group
              || regs.REG_SYSCALL == SYS_exit) {
      if (regs.REG_ARG0 != 0) {
        return SandboxReply::RUNTIME_ERROR;
      }
    }
			// Check if is a black listed syscall
    else if (regs.REG_SYSCALL < N_SYSCALLS
              && syscall_blocked[regs.REG_SYSCALL]) {
      ptrace(PTRACE_KILL, pid, 0, 0);
      return SandboxReply::ILLEGAL_INSTRUCTION;
    }
      // Continue process until next syscall
    ptrace(PTRACE_SYSCALL, pid, 0, 0);
  }
      // everything OK!
  if (WIFEXITED(status)) {
    int exit_code = WEXITSTATUS(status);
    if (!exit_code) {
      return SandboxReply::SUCCESS;
    }
    return SandboxReply::INTERNAL_ERROR;
  }
    // was term by signal
  else if (WIFSIGNALED(status)) {
    int sig = WTERMSIG(status);
		
    if (sig == SIGKILL) {
      Sandbox::memory_used = memory_limit + 1;
      return SandboxReply::MEMORY_LIMIT_EXCEEDED;
    }
    else if (sig == SIGXCPU) {
      Sandbox::time_elapsed = time_limit + 1;
      return SandboxReply::TIME_LIMIT_EXCEEDED;
    }
    else if (sig == SIGXFSZ) {
      return SandboxReply::OUPUT_LIMIT_EXCEEDED;
    }
    return SandboxReply::RUNTIME_ERROR;
  }
  Sandbox::memory_used = memory_limit + 1;
  return SandboxReply::MEMORY_LIMIT_EXCEEDED;
}

