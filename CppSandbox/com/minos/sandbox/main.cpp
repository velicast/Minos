/* 
 * File:   main.cpp
 * Author: Eduar Castrillo (eduarc)
 *
 * Created on April 20, 2013, 5:16 AM
 */

#include <cstdlib>
#include <cstring>
#include <cstdio>
#include <cmath>

#include "Sandbox.h"
#include "SandboxReply.h"
#include "utils.h"

using namespace std;

/*
 * argv[0] = program name
 * argv[1] = input file
 * argv[2] = output file
 * argv[3] = time limit
 * argv[4] = memory limit
 * argv[5] = stack  limit
 * argv[6] = output limit
 * argv[7] = exec file
 */
int main(int argc, char** argv) {

  if (argc != 8) {
    return SandboxReply::INTERNAL_ERROR;
  }
	
  process_t target;
  memset(&target, 0, sizeof(process_t));
	
    // Files to redirect stdin, stdout and stderr in the target process
  target.file[0] = argv[1];
  target.file[1] = argv[2];
  target.file[2] = "/dev/null";
	
    // limits
  double t = atof(argv[3]);
  int time_limit = (int)t;
  if (time_limit < 1000) {
    target.time_limit = 1;
  } else {
    target.time_limit = (int)(t / 1000. + 0.5);
  }
  target.memory_limit = atoi(argv[4]);
  target.stack_limit  = atoi(argv[5]);
  target.output_limit = atoi(argv[6]);
	
    // executable
  target.exec = argv[7];

  int pid = create_process(target);
  if (pid == -1) {
    return SandboxReply::INTERNAL_ERROR;
  }

  int status = Sandbox::trace(pid, time_limit, target.memory_limit);
    // send statistics to server
  printf("%d %d", Sandbox::time_elapsed, Sandbox::memory_used);
  return status;
}
