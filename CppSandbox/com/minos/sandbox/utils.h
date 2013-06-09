/* 
 * File:   utils.h
 * Author: Eduar Castrillo (eduarc)
 *
 * Created on April 20, 2013, 5:25 AM
 */

#ifndef UTILS_H
#define	UTILS_H

#include <sys/resource.h>

struct process_t {
  const char *exec;
  const char *file[3];  // 0-filein 1-fileout 2-fileerr
  int time_limit;
  int memory_limit;
  int stack_limit;
  int output_limit; // output limit to files
};

int read_memory(int __pid);

int read_time(int __pid);

int read_string(int __pid, unsigned __addr, char *__buff, unsigned __max_len);

int set_limit(int __resource, rlim_t __lim);

int create_process(process_t &__process);

#endif	/* UTILS_H */

