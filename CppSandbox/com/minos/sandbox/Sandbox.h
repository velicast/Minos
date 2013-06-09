/* 
 * File:   Sandbox.h
 * Author: Eduar Castrillo (eduarc)
 *
 * Created on April 20, 2013, 5:34 AM
 */

#ifndef SANDBOX_H
#define	SANDBOX_H

#ifdef __i386
	#define REG_SYSCALL orig_eax
	#define REG_RET eax
	#define REG_ARG0 ebx
	#define REG_ARG1 ecx
#else
#ifdef __x86_64
	#define REG_SYSCALL orig_rax
	#define REG_RET rax
	#define REG_ARG0 rdi
	#define REG_ARG1 rsi
#endif
#endif

class Sandbox {
public:
  static int trace(int __pid, int __time_limit, int __memory_limit);
  static int memory_used;
  static int time_elapsed;
private:
	static void update_statistics(int __pid);
	static bool allowed_to_open(char *__path, int __mode);
};


#endif	/* SANDBOX_H */

