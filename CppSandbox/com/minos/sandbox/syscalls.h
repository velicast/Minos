/* 
 * File:   syscalls.h
 * Author: Eduar Castrillo (eduarc)
 *
 * Created on April 20, 2013, 5:33 AM
 */

#ifndef SYSCALLS_H
#define	SYSCALLS_H

#include <sys/syscall.h>

#define N_SYSCALLS 351

// true if the syscall if blocked
bool syscall_blocked[N_SYSCALLS];
// Name of the syscall
const char *syscall_name[N_SYSCALLS];

int init_syscall_table() {
	
// ----------
// BLACK LIST
// ----------
#ifdef __NR_restart_syscall
  syscall_blocked[__NR_restart_syscall] = true;
#endif
#ifdef __NR_fork
  syscall_blocked[__NR_fork] = true;
#endif
#ifdef __NR_waitpid
  syscall_blocked[__NR_waitpid] = true;
#endif
#ifdef __NR_creat
  syscall_blocked[__NR_creat] = true;
#endif
#ifdef __NR_link
  syscall_blocked[__NR_link] = true;
#endif
#ifdef __NR_unlink
  syscall_blocked[__NR_unlink] = true;
#endif
#ifdef __NR_chdir
  syscall_blocked[__NR_chdir] = true;
#endif
#ifdef __NR_mknod
  syscall_blocked[__NR_mknod] = true;
#endif
#ifdef __NR_chmod
  syscall_blocked[__NR_chmod] = true;
#endif
#ifdef __NR_lchown
  syscall_blocked[__NR_lchown] = true;
#endif
#ifdef __NR_break
  syscall_blocked[__NR_break] = true;
#endif
#ifdef __NR_oldstat
  syscall_blocked[__NR_oldstat] = true;
#endif
#ifdef __NR_lseek
  syscall_blocked[__NR_lseek] = true;
#endif
#ifdef __NR_getpid
  syscall_blocked[__NR_getpid] = true;
#endif
#ifdef __NR_mount
  syscall_blocked[__NR_mount] = true;
#endif
#ifdef __NR_umount
  syscall_blocked[__NR_umount] = true;
#endif
#ifdef __NR_setuid
  syscall_blocked[__NR_setuid] = true;
#endif
#ifdef __NR_getuid
  syscall_blocked[__NR_getuid] = true;
#endif
#ifdef __NR_stime
  syscall_blocked[__NR_stime] = true;
#endif
#ifdef __NR_ptrace
  syscall_blocked[__NR_ptrace] = true;
#endif
#ifdef __NR_alarm
  syscall_blocked[__NR_alarm] = true;
#endif
#ifdef __NR_oldfstat
  syscall_blocked[__NR_oldfstat] = true;
#endif
#ifdef __NR_pause
  syscall_blocked[__NR_pause] = true;
#endif
#ifdef __NR_utime
  syscall_blocked[__NR_utime] = true;
#endif
#ifdef __NR_stty
  syscall_blocked[__NR_stty] = true;
#endif
#ifdef __NR_gtty
  syscall_blocked[__NR_gtty] = true;
#endif
#ifdef __NR_nice
  syscall_blocked[__NR_nice] = true;
#endif
#ifdef __NR_ftime
  syscall_blocked[__NR_ftime] = true;
#endif
#ifdef __NR_sync
  syscall_blocked[__NR_sync] = true;
#endif
#ifdef __NR_kill
  syscall_blocked[__NR_kill] = true;
#endif
#ifdef __NR_rename
  syscall_blocked[__NR_rename] = true;
#endif
#ifdef __NR_mkdir
  syscall_blocked[__NR_mkdir] = true;
#endif
#ifdef __NR_rmdir
  syscall_blocked[__NR_rmdir] = true;
#endif
#ifdef __NR_dup
  syscall_blocked[__NR_dup] = true;
#endif
#ifdef __NR_pipe
  syscall_blocked[__NR_pipe] = true;
#endif
#ifdef __NR_times
  syscall_blocked[__NR_times] = true;
#endif
#ifdef __NR_prof
  syscall_blocked[__NR_prof] = true;
#endif
#ifdef __NR_setgid
  syscall_blocked[__NR_setgid] = true;
#endif
#ifdef __NR_getgid
  syscall_blocked[__NR_getgid] = true;
#endif
#ifdef __NR_signal
  syscall_blocked[__NR_signal] = true;
#endif
#ifdef __NR_geteuid
  syscall_blocked[__NR_geteuid] = true;
#endif
#ifdef __NR_getegid
  syscall_blocked[__NR_getegid] = true;
#endif
#ifdef __NR_acct
  syscall_blocked[__NR_acct] = true;
#endif
#ifdef __NR_umount2
  syscall_blocked[__NR_umount2] = true;
#endif
#ifdef __NR_lock
  syscall_blocked[__NR_lock] = true;
#endif
#ifdef __NR_ioctl
  syscall_blocked[__NR_ioctl] = true;
#endif
#ifdef __NR_fcntl
  syscall_blocked[__NR_fcntl] = true;
#endif
#ifdef __NR_mpx
  syscall_blocked[__NR_mpx] = true;
#endif
#ifdef __NR_setpgid
  syscall_blocked[__NR_setpgid] = true;
#endif
#ifdef __NR_ulimit
  syscall_blocked[__NR_ulimit] = true;
#endif
#ifdef __NR_oldolduname
  syscall_blocked[__NR_oldolduname] = true;
#endif
#ifdef __NR_chroot
  syscall_blocked[__NR_chroot] = true;
#endif
#ifdef __NR_ustat
  syscall_blocked[__NR_ustat] = true;
#endif
#ifdef __NR_dup2
  syscall_blocked[__NR_dup2] = true;
#endif
#ifdef __NR_getppid
  syscall_blocked[__NR_getppid] = true;
#endif
#ifdef __NR_getpgrp
  syscall_blocked[__NR_getpgrp] = true;
#endif
#ifdef __NR_setsid
  syscall_blocked[__NR_setsid] = true;
#endif
#ifdef __NR_setreuid
  syscall_blocked[__NR_setreuid] = true;
#endif
#ifdef __NR_setregid
  syscall_blocked[__NR_setregid] = true;
#endif
#ifdef __NR_sethostname
  syscall_blocked[__NR_sethostname] = true;
#endif
#ifdef __NR_setrlimit
  syscall_blocked[__NR_setrlimit] = true;
#endif
#ifdef __NR_getrlimit
  syscall_blocked[__NR_getrlimit] = true;
#endif
#ifdef __NR_getrusage
  syscall_blocked[__NR_getrusage] = true;
#endif
#ifdef __NR_settimeofday
  syscall_blocked[__NR_settimeofday] = true;
#endif
#ifdef __NR_getgroups
  syscall_blocked[__NR_getgroups] = true;
#endif
#ifdef __NR_setgroups
  syscall_blocked[__NR_setgroups] = true;
#endif
#ifdef __NR_symlink
  syscall_blocked[__NR_symlink] = true;
#endif
#ifdef __NR_oldlstat
  syscall_blocked[__NR_oldlstat] = true;
#endif
#ifdef __NR_readlink
  syscall_blocked[__NR_readlink] = true;
#endif
#ifdef __NR_swapon
  syscall_blocked[__NR_swapon] = true;
#endif
#ifdef __NR_reboot
  syscall_blocked[__NR_reboot] = true;
#endif
#ifdef __NR_readdir
  syscall_blocked[__NR_readdir] = true;
#endif
#ifdef __NR_truncate
  syscall_blocked[__NR_truncate] = true;
#endif
#ifdef __NR_ftruncate
  syscall_blocked[__NR_ftruncate] = true;
#endif
#ifdef __NR_fchmod
  syscall_blocked[__NR_fchmod] = true;
#endif
#ifdef __NR_fchown
  syscall_blocked[__NR_fchown] = true;
#endif
#ifdef __NR_getpriority
  syscall_blocked[__NR_getpriority] = true;
#endif
#ifdef __NR_setpriority
  syscall_blocked[__NR_setpriority] = true;
#endif
#ifdef __NR_profil
  syscall_blocked[__NR_profil] = true;
#endif
#ifdef __NR_statfs
  syscall_blocked[__NR_statfs] = true;
#endif
#ifdef __NR_fstatfs
  syscall_blocked[__NR_fstatfs] = true;
#endif
#ifdef __NR_ioperm
  syscall_blocked[__NR_ioperm] = true;
#endif
#ifdef __NR_socketcall
  syscall_blocked[__NR_socketcall] = true;
#endif
#ifdef __NR_syslog
  syscall_blocked[__NR_syslog] = true;
#endif
#ifdef __NR_setitimer
  syscall_blocked[__NR_setitimer] = true;
#endif
#ifdef __NR_olduname
  syscall_blocked[__NR_olduname] = true;
#endif
#ifdef __NR_iopl
  syscall_blocked[__NR_iopl] = true;
#endif
#ifdef __NR_vhangup
  syscall_blocked[__NR_vhangup] = true;
#endif
#ifdef __NR_idle
  syscall_blocked[__NR_idle] = true;
#endif
#ifdef __NR_vm86old
  syscall_blocked[__NR_vm86old] = true;
#endif
#ifdef __NR_wait4
  syscall_blocked[__NR_wait4] = true;
#endif
#ifdef __NR_swapoff
  syscall_blocked[__NR_swapoff] = true;
#endif
#ifdef __NR_sysinfo
  syscall_blocked[__NR_sysinfo] = true;
#endif
#ifdef __NR_ipc
  syscall_blocked[__NR_ipc] = true;
#endif
#ifdef __NR_fsync
  syscall_blocked[__NR_fsync] = true;
#endif
#ifdef __NR_sigreturn
  syscall_blocked[__NR_sigreturn] = true;
#endif
#ifdef __NR_clone
  syscall_blocked[__NR_clone] = true;
#endif
#ifdef __NR_setdomainname
  syscall_blocked[__NR_setdomainname] = true;
#endif
#ifdef __NR_modify_ldt
  syscall_blocked[__NR_modify_ldt] = true;
#endif
#ifdef __NR_adjtimex
  syscall_blocked[__NR_adjtimex] = true;
#endif
#ifdef __NR_sigprocmask
  syscall_blocked[__NR_sigprocmask] = true;
#endif
#ifdef __NR_create_module
  syscall_blocked[__NR_create_module] = true;
#endif
#ifdef __NR_init_module
  syscall_blocked[__NR_init_module] = true;
#endif
#ifdef __NR_delete_module
  syscall_blocked[__NR_delete_module] = true;
#endif
#ifdef __NR_get_kernel_syms
  syscall_blocked[__NR_get_kernel_syms] = true;
#endif
#ifdef __NR_quotactl
  syscall_blocked[__NR_quotactl] = true;
#endif
#ifdef __NR_getpgid
  syscall_blocked[__NR_getpgid] = true;
#endif
#ifdef __NR_fchdir
  syscall_blocked[__NR_fchdir] = true;
#endif
#ifdef __NR_bdflush
  syscall_blocked[__NR_bdflush] = true;
#endif
#ifdef __NR_sysfs
  syscall_blocked[__NR_sysfs] = true;
#endif
#ifdef __NR_personality
  syscall_blocked[__NR_personality] = true;
#endif
#ifdef __NR_afs_syscall
  syscall_blocked[__NR_afs_syscall] = true;
#endif
#ifdef __NR_setfsuid
  syscall_blocked[__NR_setfsuid] = true;
#endif
#ifdef __NR_setfsgid
  syscall_blocked[__NR_setfsgid] = true;
#endif
#ifdef __NR__llseek
  syscall_blocked[__NR__llseek] = true;
#endif
#ifdef __NR_getdents
  syscall_blocked[__NR_getdents] = true;
#endif
#ifdef __NR__newselect
  syscall_blocked[__NR__newselect] = true;
#endif
#ifdef __NR_msync
  syscall_blocked[__NR_msync] = true;
#endif
#ifdef __NR_getsid
  syscall_blocked[__NR_getsid] = true;
#endif
#ifdef __NR_fdatasync
  syscall_blocked[__NR_fdatasync] = true;
#endif
#ifdef __NR__sysctl
  syscall_blocked[__NR__sysctl] = true;
#endif
#ifdef __NR_sched_setparam
  syscall_blocked[__NR_sched_setparam] = true;
#endif
#ifdef __NR_sched_setscheduler
  syscall_blocked[__NR_sched_setscheduler] = true;
#endif
#ifdef __NR_sched_yield
  syscall_blocked[__NR_sched_yield] = true;
#endif
#ifdef __NR_nanosleep
  syscall_blocked[__NR_nanosleep] = true;
#endif
#ifdef __NR_setresuid
  syscall_blocked[__NR_setresuid] = true;
#endif
#ifdef __NR_getresuid
  syscall_blocked[__NR_getresuid] = true;
#endif
#ifdef __NR_vm86
  syscall_blocked[__NR_vm86] = true;
#endif
#ifdef __NR_query_module
  syscall_blocked[__NR_query_module] = true;
#endif
#ifdef __NR_nfsservctl
  syscall_blocked[__NR_nfsservctl] = true;
#endif
#ifdef __NR_setresgid
  syscall_blocked[__NR_setresgid] = true;
#endif
#ifdef __NR_getresgid
  syscall_blocked[__NR_getresgid] = true;
#endif
#ifdef __NR_prctl
  syscall_blocked[__NR_prctl] = true;
#endif
#ifdef __NR_pread64
  syscall_blocked[__NR_pread64] = true;
#endif
#ifdef __NR_pwrite64
  syscall_blocked[__NR_pwrite64] = true;
#endif
#ifdef __NR_chown
  syscall_blocked[__NR_chown] = true;
#endif
#ifdef __NR_getcwd
  syscall_blocked[__NR_getcwd] = true;
#endif
#ifdef __NR_capget
  syscall_blocked[__NR_capget] = true;
#endif
#ifdef __NR_capset
  syscall_blocked[__NR_capset] = true;
#endif
#ifdef __NR_sendfile
  syscall_blocked[__NR_sendfile] = true;
#endif
#ifdef __NR_getpmsg
  syscall_blocked[__NR_getpmsg] = true;
#endif
#ifdef __NR_putpmsg
  syscall_blocked[__NR_putpmsg] = true;
#endif
#ifdef __NR_vfork
  syscall_blocked[__NR_vfork] = true;
#endif
#ifdef __NR_ugetrlimit
  syscall_blocked[__NR_ugetrlimit] = true;
#endif
#ifdef __NR_truncate64
  syscall_blocked[__NR_truncate64] = true;
#endif
#ifdef __NR_ftruncate64
  syscall_blocked[__NR_ftruncate64] = true;
#endif
#ifdef __NR_lchown32
  syscall_blocked[__NR_lchown32] = true;
#endif
#ifdef __NR_getuid32
  syscall_blocked[__NR_getuid32] = true;
#endif
#ifdef __NR_getgid32
  syscall_blocked[__NR_getgid32] = true;
#endif
#ifdef __NR_geteuid32
  syscall_blocked[__NR_geteuid32] = true;
#endif
#ifdef __NR_getegid32
  syscall_blocked[__NR_getegid32] = true;
#endif
#ifdef __NR_setreuid32
  syscall_blocked[__NR_setreuid32] = true;
#endif
#ifdef __NR_setregid32
  syscall_blocked[__NR_setregid32] = true;
#endif
#ifdef __NR_getgroups32
  syscall_blocked[__NR_getgroups32] = true;
#endif
#ifdef __NR_setgroups32
  syscall_blocked[__NR_setgroups32] = true;
#endif
#ifdef __NR_fchown32
  syscall_blocked[__NR_fchown32] = true;
#endif
#ifdef __NR_setresuid32
  syscall_blocked[__NR_setresuid32] = true;
#endif
#ifdef __NR_getresuid32
  syscall_blocked[__NR_getresuid32] = true;
#endif
#ifdef __NR_setresgid32
  syscall_blocked[__NR_setresgid32] = true;
#endif
#ifdef __NR_getresgid32
  syscall_blocked[__NR_getresgid32] = true;
#endif
#ifdef __NR_chown32
  syscall_blocked[__NR_chown32] = true;
#endif
#ifdef __NR_setuid32
  syscall_blocked[__NR_setuid32] = true;
#endif
#ifdef __NR_setgid32
  syscall_blocked[__NR_setgid32] = true;
#endif
#ifdef __NR_setfsuid32
  syscall_blocked[__NR_setfsuid32] = true;
#endif
#ifdef __NR_setfsgid32
  syscall_blocked[__NR_setfsgid32] = true;
#endif
#ifdef __NR_pivot_root
  syscall_blocked[__NR_pivot_root] = true;
#endif
#ifdef __NR_mincore
  syscall_blocked[__NR_mincore] = true;
#endif
#ifdef __NR_madvise
  syscall_blocked[__NR_madvise] = true;
#endif
#ifdef __NR_getdents64
  syscall_blocked[__NR_getdents64] = true;
#endif
#ifdef __NR_fcntl64
  syscall_blocked[__NR_fcntl64] = true;
#endif
#ifdef __NR_gettid
  syscall_blocked[__NR_gettid] = true;
#endif
#ifdef __NR_readahead
  syscall_blocked[__NR_readahead] = true;
#endif
#ifdef __NR_setxattr
  syscall_blocked[__NR_setxattr] = true;
#endif
#ifdef __NR_lsetxattr
  syscall_blocked[__NR_lsetxattr] = true;
#endif
#ifdef __NR_fsetxattr
  syscall_blocked[__NR_fsetxattr] = true;
#endif
#ifdef __NR_getxattr
  syscall_blocked[__NR_getxattr] = true;
#endif
#ifdef __NR_lgetxattr
  syscall_blocked[__NR_lgetxattr] = true;
#endif
#ifdef __NR_fgetxattr
  syscall_blocked[__NR_fgetxattr] = true;
#endif
#ifdef __NR_listxattr
  syscall_blocked[__NR_listxattr] = true;
#endif
#ifdef __NR_llistxattr
  syscall_blocked[__NR_llistxattr] = true;
#endif
#ifdef __NR_flistxattr
  syscall_blocked[__NR_flistxattr] = true;
#endif
#ifdef __NR_lremovexattr
  syscall_blocked[__NR_lremovexattr] = true;
#endif
#ifdef __NR_fremovexattr
  syscall_blocked[__NR_fremovexattr] = true;
#endif
#ifdef __NR_tkill
  syscall_blocked[__NR_tkill] = true;
#endif
#ifdef __NR_sendfile64
  syscall_blocked[__NR_sendfile64] = true;
#endif
#ifdef __NR_futex
  syscall_blocked[__NR_futex] = true;
#endif
#ifdef __NR_sched_setaffinity
  syscall_blocked[__NR_sched_setaffinity] = true;
#endif
#ifdef __NR_sched_getaffinity
  syscall_blocked[__NR_sched_getaffinity] = true;
#endif
#ifdef __NR_get_thread_area
  syscall_blocked[__NR_get_thread_area] = true;
#endif
#ifdef __NR_io_setup
  syscall_blocked[__NR_io_setup] = true;
#endif
#ifdef __NR_io_destroy
  syscall_blocked[__NR_io_destroy] = true;
#endif
#ifdef __NR_io_getevents
  syscall_blocked[__NR_io_getevents] = true;
#endif
#ifdef __NR_io_submit
  syscall_blocked[__NR_io_submit] = true;
#endif
#ifdef __NR_io_cancel
  syscall_blocked[__NR_io_cancel] = true;
#endif
#ifdef __NR_fadvise64
  syscall_blocked[__NR_fadvise64] = true;
#endif
#ifdef __NR_lookup_dcookie
  syscall_blocked[__NR_lookup_dcookie] = true;
#endif
#ifdef __NR_epoll_create
  syscall_blocked[__NR_epoll_create] = true;
#endif
#ifdef __NR_epoll_ctl
  syscall_blocked[__NR_epoll_ctl] = true;
#endif
#ifdef __NR_epoll_wait
  syscall_blocked[__NR_epoll_wait] = true;
#endif
#ifdef __NR_remap_file_pages
  syscall_blocked[__NR_remap_file_pages] = true;
#endif
#ifdef __NR_set_tid_address
  syscall_blocked[__NR_set_tid_address] = true;
#endif
#ifdef __NR_timer_create
  syscall_blocked[__NR_timer_create] = true;
#endif
#ifdef __NR_timer_settime
  syscall_blocked[__NR_timer_settime] = true;
#endif
#ifdef __NR_timer_getoverrun
  syscall_blocked[__NR_timer_getoverrun] = true;
#endif
#ifdef __NR_timer_delete
  syscall_blocked[__NR_timer_delete] = true;
#endif
#ifdef __NR_clock_settime
  syscall_blocked[__NR_clock_settime] = true;
#endif
#ifdef __NR_clock_gettime
  syscall_blocked[__NR_clock_gettime] = true;
#endif
#ifdef __NR_clock_getres
  syscall_blocked[__NR_clock_getres] = true;
#endif
#ifdef __NR_clock_nanosleep
  syscall_blocked[__NR_clock_nanosleep] = true;
#endif
#ifdef __NR_statfs64
  syscall_blocked[__NR_statfs64] = true;
#endif
#ifdef __NR_fstatfs64
  syscall_blocked[__NR_fstatfs64] = true;
#endif
#ifdef __NR_tgkill
  syscall_blocked[__NR_tgkill] = true;
#endif
#ifdef __NR_utimes
  syscall_blocked[__NR_utimes] = true;
#endif
#ifdef __NR_fadvise64_64
  syscall_blocked[__NR_fadvise64_64] = true;
#endif
#ifdef __NR_vserver
  syscall_blocked[__NR_vserver] = true;
#endif
#ifdef __NR_mbind
  syscall_blocked[__NR_mbind] = true;
#endif
#ifdef __NR_kexec_load
  syscall_blocked[__NR_kexec_load] = true;
#endif
#ifdef __NR_waitid
  syscall_blocked[__NR_waitid] = true;
#endif
#ifdef __NR_add_key
  syscall_blocked[__NR_add_key] = true;
#endif
#ifdef __NR_request_key
  syscall_blocked[__NR_request_key] = true;
#endif
#ifdef __NR_keyctl
  syscall_blocked[__NR_keyctl] = true;
#endif
#ifdef __NR_ioprio_set
  syscall_blocked[__NR_ioprio_set] = true;
#endif
#ifdef __NR_ioprio_get
  syscall_blocked[__NR_ioprio_get] = true;
#endif
#ifdef __NR_inotify_init
  syscall_blocked[__NR_inotify_init] = true;
#endif
#ifdef __NR_inotify_add_watch
  syscall_blocked[__NR_inotify_add_watch] = true;
#endif
#ifdef __NR_inotify_rm_watch
  syscall_blocked[__NR_inotify_rm_watch] = true;
#endif
#ifdef __NR_migrate_pages
  syscall_blocked[__NR_migrate_pages] = true;
#endif
#ifdef __NR_openat
  syscall_blocked[__NR_openat] = true;
#endif
#ifdef __NR_mkdirat
  syscall_blocked[__NR_mkdirat] = true;
#endif
#ifdef __NR_mknodat
  syscall_blocked[__NR_mknodat] = true;
#endif
#ifdef __NR_fchownat
  syscall_blocked[__NR_fchownat] = true;
#endif
#ifdef __NR_futimesat
  syscall_blocked[__NR_futimesat] = true;
#endif
#ifdef __NR_fstatat64
  syscall_blocked[__NR_fstatat64] = true;
#endif
#ifdef __NR_unlinkat
  syscall_blocked[__NR_unlinkat] = true;
#endif
#ifdef __NR_renameat
  syscall_blocked[__NR_renameat] = true;
#endif
#ifdef __NR_linkat
  syscall_blocked[__NR_linkat] = true;
#endif
#ifdef __NR_symlinkat
  syscall_blocked[__NR_symlinkat] = true;
#endif
#ifdef __NR_readlinkat
  syscall_blocked[__NR_readlinkat] = true;
#endif
#ifdef __NR_fchmodat
  syscall_blocked[__NR_fchmodat] = true;
#endif
#ifdef __NR_faccessat
  syscall_blocked[__NR_faccessat] = true;
#endif
#ifdef __NR_pselect6
  syscall_blocked[__NR_pselect6] = true;
#endif
#ifdef __NR_ppoll
  syscall_blocked[__NR_ppoll] = true;
#endif
#ifdef __NR_unshare
  syscall_blocked[__NR_unshare] = true;
#endif
#ifdef __NR_set_robust_list
  syscall_blocked[__NR_set_robust_list] = true;
#endif
#ifdef __NR_get_robust_list
  syscall_blocked[__NR_get_robust_list] = true;
#endif
#ifdef __NR_splice
  syscall_blocked[__NR_splice] = true;
#endif
#ifdef __NR_sync_file_range
  syscall_blocked[__NR_sync_file_range] = true;
#endif
#ifdef __NR_tee
  syscall_blocked[__NR_tee] = true;
#endif
#ifdef __NR_vmsplice
  syscall_blocked[__NR_vmsplice] = true;
#endif
#ifdef __NR_move_pages
  syscall_blocked[__NR_move_pages] = true;
#endif
#ifdef __NR_getcpu
  syscall_blocked[__NR_getcpu] = true;
#endif
#ifdef __NR_epoll_pwait
  syscall_blocked[__NR_epoll_pwait] = true;
#endif
#ifdef __NR_utimensat
  syscall_blocked[__NR_utimensat] = true;
#endif
#ifdef __NR_signalfd
  syscall_blocked[__NR_signalfd] = true;
#endif
#ifdef __NR_timerfd_create
  syscall_blocked[__NR_timerfd_create] = true;
#endif
#ifdef __NR_eventfd
  syscall_blocked[__NR_eventfd] = true;
#endif
#ifdef __NR_timerfd_settime
  syscall_blocked[__NR_timerfd_settime] = true;
#endif
#ifdef __NR_timerfd_gettime
  syscall_blocked[__NR_timerfd_gettime] = true;
#endif
#ifdef __NR_signalfd4
  syscall_blocked[__NR_signalfd4] = true;
#endif
#ifdef __NR_eventfd2
  syscall_blocked[__NR_eventfd2] = true;
#endif
#ifdef __NR_epoll_create1
  syscall_blocked[__NR_epoll_create1] = true;
#endif
#ifdef __NR_dup3
  syscall_blocked[__NR_dup3] = true;
#endif
#ifdef __NR_pipe2
  syscall_blocked[__NR_pipe2] = true;
#endif
#ifdef __NR_inotify_init1
  syscall_blocked[__NR_inotify_init1] = true;
#endif
#ifdef __NR_rt_tgsigqueueinfo
  syscall_blocked[__NR_rt_tgsigqueueinfo] = true;
#endif
#ifdef __NR_perf_event_open
  syscall_blocked[__NR_perf_event_open] = true;
#endif
#ifdef __NR_recvmmsg
  syscall_blocked[__NR_recvmmsg] = true;
#endif
#ifdef __NR_fanotify_init
  syscall_blocked[__NR_fanotify_init] = true;
#endif
#ifdef __NR_fanotify_mark
  syscall_blocked[__NR_fanotify_mark] = true;
#endif
#ifdef __NR_prlimit64
  syscall_blocked[__NR_prlimit64] = true;
#endif
#ifdef __NR_name_to_handle_at
  syscall_blocked[__NR_name_to_handle_at] = true;
#endif
#ifdef __NR_open_by_handle_at
  syscall_blocked[__NR_open_by_handle_at] = true;
#endif
#ifdef __NR_clock_adjtime
  syscall_blocked[__NR_clock_adjtime] = true;
#endif
#ifdef __NR_syncfs
  syscall_blocked[__NR_syncfs] = true;
#endif
#ifdef __NR_sendmmsg
  syscall_blocked[__NR_sendmmsg] = true;
#endif
#ifdef __NR_setns
  syscall_blocked[__NR_setns] = true;
#endif
#ifdef __NR_process_vm_readv
  syscall_blocked[__NR_process_vm_readv] = true;
#endif
#ifdef __NR_process_vm_writev
  syscall_blocked[__NR_process_vm_writev] = true;
#endif
#ifdef __NR_kcmp
  syscall_blocked[__NR_kcmp] = true;
#endif
#ifdef __NR_finit_module
  syscall_blocked[__NR_finit_module] = true;
#endif

// -----
// NAMES
// -----

#ifdef __NR_restart_syscall
  syscall_name[__NR_restart_syscall] = "__NR_restart_syscall";
#endif
#ifdef __NR_exit
  syscall_name[__NR_exit] = "__NR_exit";
#endif
#ifdef __NR_fork
  syscall_name[__NR_fork] = "__NR_fork";
#endif
#ifdef __NR_read
  syscall_name[__NR_read] = "__NR_read";
#endif
#ifdef __NR_write
  syscall_name[__NR_write] = "__NR_write";
#endif
#ifdef __NR_open
  syscall_name[__NR_open] = "__NR_open";
#endif
#ifdef __NR_close
  syscall_name[__NR_close] = "__NR_close";
#endif
#ifdef __NR_waitpid
  syscall_name[__NR_waitpid] = "__NR_waitpid";
#endif
#ifdef __NR_creat
  syscall_name[__NR_creat] = "__NR_creat";
#endif
#ifdef __NR_link
  syscall_name[__NR_link] = "__NR_link";
#endif
#ifdef __NR_unlink
  syscall_name[__NR_unlink] = "__NR_unlink";
#endif
#ifdef __NR_execve
  syscall_name[__NR_execve] = "__NR_execve";
#endif
#ifdef __NR_chdir
  syscall_name[__NR_chdir] = "__NR_chdir";
#endif
#ifdef __NR_time
  syscall_name[__NR_time] = "__NR_time";
#endif
#ifdef __NR_mknod
  syscall_name[__NR_mknod] = "__NR_mknod";
#endif
#ifdef __NR_chmod
  syscall_name[__NR_chmod] = "__NR_chmod";
#endif
#ifdef __NR_lchown
  syscall_name[__NR_lchown] = "__NR_lchown";
#endif
#ifdef __NR_break
  syscall_name[__NR_break] = "__NR_break";
#endif
#ifdef __NR_oldstat
  syscall_name[__NR_oldstat] = "__NR_oldstat";
#endif
#ifdef __NR_lseek
  syscall_name[__NR_lseek] = "__NR_lseek";
#endif
#ifdef __NR_getpid
  syscall_name[__NR_getpid] = "__NR_getpid";
#endif
#ifdef __NR_mount
  syscall_name[__NR_mount] = "__NR_mount";
#endif
#ifdef __NR_umount
  syscall_name[__NR_umount] = "__NR_umount";
#endif
#ifdef __NR_setuid
  syscall_name[__NR_setuid] = "__NR_setuid";
#endif
#ifdef __NR_getuid
  syscall_name[__NR_getuid] = "__NR_getuid";
#endif
#ifdef __NR_stime
  syscall_name[__NR_stime] = "__NR_stime";
#endif
#ifdef __NR_ptrace
  syscall_name[__NR_ptrace] = "__NR_ptrace";
#endif
#ifdef __NR_alarm
  syscall_name[__NR_alarm] = "__NR_alarm";
#endif
#ifdef __NR_oldfstat
  syscall_name[__NR_oldfstat] = "__NR_oldfstat";
#endif
#ifdef __NR_pause
  syscall_name[__NR_pause] = "__NR_pause";
#endif
#ifdef __NR_utime
  syscall_name[__NR_utime] = "__NR_utime";
#endif
#ifdef __NR_stty
  syscall_name[__NR_stty] = "__NR_stty";
#endif
#ifdef __NR_gtty
  syscall_name[__NR_gtty] = "__NR_gtty";
#endif
#ifdef __NR_access
  syscall_name[__NR_access] = "__NR_access";
#endif
#ifdef __NR_nice
  syscall_name[__NR_nice] = "__NR_nice";
#endif
#ifdef __NR_ftime
  syscall_name[__NR_ftime] = "__NR_ftime";
#endif
#ifdef __NR_sync
  syscall_name[__NR_sync] = "__NR_sync";
#endif
#ifdef __NR_kill
  syscall_name[__NR_kill] = "__NR_kill";
#endif
#ifdef __NR_rename
  syscall_name[__NR_rename] = "__NR_rename";
#endif
#ifdef __NR_mkdir
  syscall_name[__NR_mkdir] = "__NR_mkdir";
#endif
#ifdef __NR_rmdir
  syscall_name[__NR_rmdir] = "__NR_rmdir";
#endif
#ifdef __NR_dup
  syscall_name[__NR_dup] = "__NR_dup";
#endif
#ifdef __NR_pipe
  syscall_name[__NR_pipe] = "__NR_pipe";
#endif
#ifdef __NR_times
  syscall_name[__NR_times] = "__NR_times";
#endif
#ifdef __NR_prof
  syscall_name[__NR_prof] = "__NR_prof";
#endif
#ifdef __NR_brk
  syscall_name[__NR_brk] = "__NR_brk";
#endif
#ifdef __NR_setgid
  syscall_name[__NR_setgid] = "__NR_setgid";
#endif
#ifdef __NR_getgid
  syscall_name[__NR_getgid] = "__NR_getgid";
#endif
#ifdef __NR_signal
  syscall_name[__NR_signal] = "__NR_signal";
#endif
#ifdef __NR_geteuid
  syscall_name[__NR_geteuid] = "__NR_geteuid";
#endif
#ifdef __NR_getegid
  syscall_name[__NR_getegid] = "__NR_getegid";
#endif
#ifdef __NR_acct
  syscall_name[__NR_acct] = "__NR_acct";
#endif
#ifdef __NR_umount2
  syscall_name[__NR_umount2] = "__NR_umount2";
#endif
#ifdef __NR_lock
  syscall_name[__NR_lock] = "__NR_lock";
#endif
#ifdef __NR_ioctl
  syscall_name[__NR_ioctl] = "__NR_ioctl";
#endif
#ifdef __NR_fcntl
  syscall_name[__NR_fcntl] = "__NR_fcntl";
#endif
#ifdef __NR_mpx
  syscall_name[__NR_mpx] = "__NR_mpx";
#endif
#ifdef __NR_setpgid
  syscall_name[__NR_setpgid] = "__NR_setpgid";
#endif
#ifdef __NR_ulimit
  syscall_name[__NR_ulimit] = "__NR_ulimit";
#endif
#ifdef __NR_oldolduname
  syscall_name[__NR_oldolduname] = "__NR_oldolduname";
#endif
#ifdef __NR_umask
  syscall_name[__NR_umask] = "__NR_umask";
#endif
#ifdef __NR_chroot
  syscall_name[__NR_chroot] = "__NR_chroot";
#endif
#ifdef __NR_ustat
  syscall_name[__NR_ustat] = "__NR_ustat";
#endif
#ifdef __NR_dup2
  syscall_name[__NR_dup2] = "__NR_dup2";
#endif
#ifdef __NR_getppid
  syscall_name[__NR_getppid] = "__NR_getppid";
#endif
#ifdef __NR_getpgrp
  syscall_name[__NR_getpgrp] = "__NR_getpgrp";
#endif
#ifdef __NR_setsid
  syscall_name[__NR_setsid] = "__NR_setsid";
#endif
#ifdef __NR_sigaction
  syscall_name[__NR_sigaction] = "__NR_sigaction";
#endif
#ifdef __NR_sgetmask
  syscall_name[__NR_sgetmask] = "__NR_sgetmask";
#endif
#ifdef __NR_ssetmask
  syscall_name[__NR_ssetmask] = "__NR_ssetmask";
#endif
#ifdef __NR_setreuid
  syscall_name[__NR_setreuid] = "__NR_setreuid";
#endif
#ifdef __NR_setregid
  syscall_name[__NR_setregid] = "__NR_setregid";
#endif
#ifdef __NR_sigsuspend
  syscall_name[__NR_sigsuspend] = "__NR_sigsuspend";
#endif
#ifdef __NR_sigpending
  syscall_name[__NR_sigpending] = "__NR_sigpending";
#endif
#ifdef __NR_sethostname
  syscall_name[__NR_sethostname] = "__NR_sethostname";
#endif
#ifdef __NR_setrlimit
  syscall_name[__NR_setrlimit] = "__NR_setrlimit";
#endif
#ifdef __NR_getrlimit
  syscall_name[__NR_getrlimit] = "__NR_getrlimit";
#endif
#ifdef __NR_getrusage
  syscall_name[__NR_getrusage] = "__NR_getrusage";
#endif
#ifdef __NR_gettimeofday
  syscall_name[__NR_gettimeofday] = "__NR_gettimeofday";
#endif
#ifdef __NR_settimeofday
  syscall_name[__NR_settimeofday] = "__NR_settimeofday";
#endif
#ifdef __NR_getgroups
  syscall_name[__NR_getgroups] = "__NR_getgroups";
#endif
#ifdef __NR_setgroups
  syscall_name[__NR_setgroups] = "__NR_setgroups";
#endif
#ifdef __NR_select
  syscall_name[__NR_select] = "__NR_select";
#endif
#ifdef __NR_symlink
  syscall_name[__NR_symlink] = "__NR_symlink";
#endif
#ifdef __NR_oldlstat
  syscall_name[__NR_oldlstat] = "__NR_oldlstat";
#endif
#ifdef __NR_readlink
  syscall_name[__NR_readlink] = "__NR_readlink";
#endif
#ifdef __NR_uselib
  syscall_name[__NR_uselib] = "__NR_uselib";
#endif
#ifdef __NR_swapon
  syscall_name[__NR_swapon] = "__NR_swapon";
#endif
#ifdef __NR_reboot
  syscall_name[__NR_reboot] = "__NR_reboot";
#endif
#ifdef __NR_readdir
  syscall_name[__NR_readdir] = "__NR_readdir";
#endif
#ifdef __NR_mmap
  syscall_name[__NR_mmap] = "__NR_mmap";
#endif
#ifdef __NR_munmap
  syscall_name[__NR_munmap] = "__NR_munmap";
#endif
#ifdef __NR_truncate
  syscall_name[__NR_truncate] = "__NR_truncate";
#endif
#ifdef __NR_ftruncate
  syscall_name[__NR_ftruncate] = "__NR_ftruncate";
#endif
#ifdef __NR_fchmod
  syscall_name[__NR_fchmod] = "__NR_fchmod";
#endif
#ifdef __NR_fchown
  syscall_name[__NR_fchown] = "__NR_fchown";
#endif
#ifdef __NR_getpriority
  syscall_name[__NR_getpriority] = "__NR_getpriority";
#endif
#ifdef __NR_setpriority
  syscall_name[__NR_setpriority] = "__NR_setpriority";
#endif
#ifdef __NR_profil
  syscall_name[__NR_profil] = "__NR_profil";
#endif
#ifdef __NR_statfs
  syscall_name[__NR_statfs] = "__NR_statfs";
#endif
#ifdef __NR_fstatfs
  syscall_name[__NR_fstatfs] = "__NR_fstatfs";
#endif
#ifdef __NR_ioperm
  syscall_name[__NR_ioperm] = "__NR_ioperm";
#endif
#ifdef __NR_socketcall
  syscall_name[__NR_socketcall] = "__NR_socketcall";
#endif
#ifdef __NR_syslog
  syscall_name[__NR_syslog] = "__NR_syslog";
#endif
#ifdef __NR_setitimer
  syscall_name[__NR_setitimer] = "__NR_setitimer";
#endif
#ifdef __NR_getitimer
  syscall_name[__NR_getitimer] = "__NR_getitimer";
#endif
#ifdef __NR_stat
  syscall_name[__NR_stat] = "__NR_stat";
#endif
#ifdef __NR_lstat
  syscall_name[__NR_lstat] = "__NR_lstat";
#endif
#ifdef __NR_fstat
  syscall_name[__NR_fstat] = "__NR_fstat";
#endif
#ifdef __NR_olduname
  syscall_name[__NR_olduname] = "__NR_olduname";
#endif
#ifdef __NR_iopl
  syscall_name[__NR_iopl] = "__NR_iopl";
#endif
#ifdef __NR_vhangup
  syscall_name[__NR_vhangup] = "__NR_vhangup";
#endif
#ifdef __NR_idle
  syscall_name[__NR_idle] = "__NR_idle";
#endif
#ifdef __NR_vm86old
  syscall_name[__NR_vm86old] = "__NR_vm86old";
#endif
#ifdef __NR_wait4
  syscall_name[__NR_wait4] = "__NR_wait4";
#endif
#ifdef __NR_swapoff
  syscall_name[__NR_swapoff] = "__NR_swapoff";
#endif
#ifdef __NR_sysinfo
  syscall_name[__NR_sysinfo] = "__NR_sysinfo";
#endif
#ifdef __NR_ipc
  syscall_name[__NR_ipc] = "__NR_ipc";
#endif
#ifdef __NR_fsync
  syscall_name[__NR_fsync] = "__NR_fsync";
#endif
#ifdef __NR_sigreturn
  syscall_name[__NR_sigreturn] = "__NR_sigreturn";
#endif
#ifdef __NR_clone
  syscall_name[__NR_clone] = "__NR_clone";
#endif
#ifdef __NR_setdomainname
  syscall_name[__NR_setdomainname] = "__NR_setdomainname";
#endif
#ifdef __NR_uname
  syscall_name[__NR_uname] = "__NR_uname";
#endif
#ifdef __NR_modify_ldt
  syscall_name[__NR_modify_ldt] = "__NR_modify_ldt";
#endif
#ifdef __NR_adjtimex
  syscall_name[__NR_adjtimex] = "__NR_adjtimex";
#endif
#ifdef __NR_mprotect
  syscall_name[__NR_mprotect] = "__NR_mprotect";
#endif
#ifdef __NR_sigprocmask
  syscall_name[__NR_sigprocmask] = "__NR_sigprocmask";
#endif
#ifdef __NR_create_module
  syscall_name[__NR_create_module] = "__NR_create_module";
#endif
#ifdef __NR_init_module
  syscall_name[__NR_init_module] = "__NR_init_module";
#endif
#ifdef __NR_delete_module
  syscall_name[__NR_delete_module] = "__NR_delete_module";
#endif
#ifdef __NR_get_kernel_syms
  syscall_name[__NR_get_kernel_syms] = "__NR_get_kernel_syms";
#endif
#ifdef __NR_quotactl
  syscall_name[__NR_quotactl] = "__NR_quotactl";
#endif
#ifdef __NR_getpgid
  syscall_name[__NR_getpgid] = "__NR_getpgid";
#endif
#ifdef __NR_fchdir
  syscall_name[__NR_fchdir] = "__NR_fchdir";
#endif
#ifdef __NR_bdflush
  syscall_name[__NR_bdflush] = "__NR_bdflush";
#endif
#ifdef __NR_sysfs
  syscall_name[__NR_sysfs] = "__NR_sysfs";
#endif
#ifdef __NR_personality
  syscall_name[__NR_personality] = "__NR_personality";
#endif
#ifdef __NR_afs_syscall
  syscall_name[__NR_afs_syscall] = "__NR_afs_syscall";
#endif
#ifdef __NR_setfsuid
  syscall_name[__NR_setfsuid] = "__NR_setfsuid";
#endif
#ifdef __NR_setfsgid
  syscall_name[__NR_setfsgid] = "__NR_setfsgid";
#endif
#ifdef __NR__llseek
  syscall_name[__NR__llseek] = "__NR__llseek";
#endif
#ifdef __NR_getdents
  syscall_name[__NR_getdents] = "__NR_getdents";
#endif
#ifdef __NR__newselect
  syscall_name[__NR__newselect] = "__NR__newselect";
#endif
#ifdef __NR_flock
  syscall_name[__NR_flock] = "__NR_flock";
#endif
#ifdef __NR_msync
  syscall_name[__NR_msync] = "__NR_msync";
#endif
#ifdef __NR_readv
  syscall_name[__NR_readv] = "__NR_readv";
#endif
#ifdef __NR_writev
  syscall_name[__NR_writev] = "__NR_writev";
#endif
#ifdef __NR_getsid
  syscall_name[__NR_getsid] = "__NR_getsid";
#endif
#ifdef __NR_fdatasync
  syscall_name[__NR_fdatasync] = "__NR_fdatasync";
#endif
#ifdef __NR__sysctl
  syscall_name[__NR__sysctl] = "__NR__sysctl";
#endif
#ifdef __NR_mlock
  syscall_name[__NR_mlock] = "__NR_mlock";
#endif
#ifdef __NR_munlock
  syscall_name[__NR_munlock] = "__NR_munlock";
#endif
#ifdef __NR_mlockall
  syscall_name[__NR_mlockall] = "__NR_mlockall";
#endif
#ifdef __NR_munlockall
  syscall_name[__NR_munlockall] = "__NR_munlockall";
#endif
#ifdef __NR_sched_setparam
  syscall_name[__NR_sched_setparam] = "__NR_sched_setparam";
#endif
#ifdef __NR_sched_getparam
  syscall_name[__NR_sched_getparam] = "__NR_sched_getparam";
#endif
#ifdef __NR_sched_setscheduler
  syscall_name[__NR_sched_setscheduler] = "__NR_sched_setscheduler";
#endif
#ifdef __NR_sched_getscheduler
  syscall_name[__NR_sched_getscheduler] = "__NR_sched_getscheduler";
#endif
#ifdef __NR_sched_yield
  syscall_name[__NR_sched_yield] = "__NR_sched_yield";
#endif
#ifdef __NR_sched_get_priority_max
  syscall_name[__NR_sched_get_priority_max] = "__NR_sched_get_priority_max";
#endif
#ifdef __NR_sched_get_priority_min
  syscall_name[__NR_sched_get_priority_min] = "__NR_sched_get_priority_min";
#endif
#ifdef __NR_sched_rr_get_interval
  syscall_name[__NR_sched_rr_get_interval] = "__NR_sched_rr_get_interval";
#endif
#ifdef __NR_nanosleep
  syscall_name[__NR_nanosleep] = "__NR_nanosleep";
#endif
#ifdef __NR_mremap
  syscall_name[__NR_mremap] = "__NR_mremap";
#endif
#ifdef __NR_setresuid
  syscall_name[__NR_setresuid] = "__NR_setresuid";
#endif
#ifdef __NR_getresuid
  syscall_name[__NR_getresuid] = "__NR_getresuid";
#endif
#ifdef __NR_vm86
  syscall_name[__NR_vm86] = "__NR_vm86";
#endif
#ifdef __NR_query_module
  syscall_name[__NR_query_module] = "__NR_query_module";
#endif
#ifdef __NR_poll
  syscall_name[__NR_poll] = "__NR_poll";
#endif
#ifdef __NR_nfsservctl
  syscall_name[__NR_nfsservctl] = "__NR_nfsservctl";
#endif
#ifdef __NR_setresgid
  syscall_name[__NR_setresgid] = "__NR_setresgid";
#endif
#ifdef __NR_getresgid
  syscall_name[__NR_getresgid] = "__NR_getresgid";
#endif
#ifdef __NR_prctl
  syscall_name[__NR_prctl] = "__NR_prctl";
#endif
#ifdef __NR_rt_sigreturn
  syscall_name[__NR_rt_sigreturn] = "__NR_rt_sigreturn";
#endif
#ifdef __NR_rt_sigaction
  syscall_name[__NR_rt_sigaction] = "__NR_rt_sigaction";
#endif
#ifdef __NR_rt_sigprocmask
  syscall_name[__NR_rt_sigprocmask] = "__NR_rt_sigprocmask";
#endif
#ifdef __NR_rt_sigpending
  syscall_name[__NR_rt_sigpending] = "__NR_rt_sigpending";
#endif
#ifdef __NR_rt_sigtimedwait
  syscall_name[__NR_rt_sigtimedwait] = "__NR_rt_sigtimedwait";
#endif
#ifdef __NR_rt_sigqueueinfo
  syscall_name[__NR_rt_sigqueueinfo] = "__NR_rt_sigqueueinfo";
#endif
#ifdef __NR_rt_sigsuspend
  syscall_name[__NR_rt_sigsuspend] = "__NR_rt_sigsuspend";
#endif
#ifdef __NR_pread64
  syscall_name[__NR_pread64] = "__NR_pread64";
#endif
#ifdef __NR_pwrite64
  syscall_name[__NR_pwrite64] = "__NR_pwrite64";
#endif
#ifdef __NR_chown
  syscall_name[__NR_chown] = "__NR_chown";
#endif
#ifdef __NR_getcwd
  syscall_name[__NR_getcwd] = "__NR_getcwd";
#endif
#ifdef __NR_capget
  syscall_name[__NR_capget] = "__NR_capget";
#endif
#ifdef __NR_capset
  syscall_name[__NR_capset] = "__NR_capset";
#endif
#ifdef __NR_sigaltstack
  syscall_name[__NR_sigaltstack] = "__NR_sigaltstack";
#endif
#ifdef __NR_sendfile
  syscall_name[__NR_sendfile] = "__NR_sendfile";
#endif
#ifdef __NR_getpmsg
  syscall_name[__NR_getpmsg] = "__NR_getpmsg";
#endif
#ifdef __NR_putpmsg
  syscall_name[__NR_putpmsg] = "__NR_putpmsg";
#endif
#ifdef __NR_vfork
  syscall_name[__NR_vfork] = "__NR_vfork";
#endif
#ifdef __NR_ugetrlimit
  syscall_name[__NR_ugetrlimit] = "__NR_ugetrlimit";
#endif
#ifdef __NR_mmap2
  syscall_name[__NR_mmap2] = "__NR_mmap2";
#endif
#ifdef __NR_truncate64
  syscall_name[__NR_truncate64] = "__NR_truncate64";
#endif
#ifdef __NR_ftruncate64
  syscall_name[__NR_ftruncate64] = "__NR_ftruncate64";
#endif
#ifdef __NR_stat64
  syscall_name[__NR_stat64] = "__NR_stat64";
#endif
#ifdef __NR_lstat64
  syscall_name[__NR_lstat64] = "__NR_lstat64";
#endif
#ifdef __NR_fstat64
  syscall_name[__NR_fstat64] = "__NR_fstat64";
#endif
#ifdef __NR_lchown32
  syscall_name[__NR_lchown32] = "__NR_lchown32";
#endif
#ifdef __NR_getuid32
  syscall_name[__NR_getuid32] = "__NR_getuid32";
#endif
#ifdef __NR_getgid32
  syscall_name[__NR_getgid32] = "__NR_getgid32";
#endif
#ifdef __NR_geteuid32
  syscall_name[__NR_geteuid32] = "__NR_geteuid32";
#endif
#ifdef __NR_getegid32
  syscall_name[__NR_getegid32] = "__NR_getegid32";
#endif
#ifdef __NR_setreuid32
  syscall_name[__NR_setreuid32] = "__NR_setreuid32";
#endif
#ifdef __NR_setregid32
  syscall_name[__NR_setregid32] = "__NR_setregid32";
#endif
#ifdef __NR_getgroups32
  syscall_name[__NR_getgroups32] = "__NR_getgroups32";
#endif
#ifdef __NR_setgroups32
  syscall_name[__NR_setgroups32] = "__NR_setgroups32";
#endif
#ifdef __NR_fchown32
  syscall_name[__NR_fchown32] = "__NR_fchown32";
#endif
#ifdef __NR_setresuid32
  syscall_name[__NR_setresuid32] = "__NR_setresuid32";
#endif
#ifdef __NR_getresuid32
  syscall_name[__NR_getresuid32] = "__NR_getresuid32";
#endif
#ifdef __NR_setresgid32
  syscall_name[__NR_setresgid32] = "__NR_setresgid32";
#endif
#ifdef __NR_getresgid32
  syscall_name[__NR_getresgid32] = "__NR_getresgid32";
#endif
#ifdef __NR_chown32
  syscall_name[__NR_chown32] = "__NR_chown32";
#endif
#ifdef __NR_setuid32
  syscall_name[__NR_setuid32] = "__NR_setuid32";
#endif
#ifdef __NR_setgid32
  syscall_name[__NR_setgid32] = "__NR_setgid32";
#endif
#ifdef __NR_setfsuid32
  syscall_name[__NR_setfsuid32] = "__NR_setfsuid32";
#endif
#ifdef __NR_setfsgid32
  syscall_name[__NR_setfsgid32] = "__NR_setfsgid32";
#endif
#ifdef __NR_pivot_root
  syscall_name[__NR_pivot_root] = "__NR_pivot_root";
#endif
#ifdef __NR_mincore
  syscall_name[__NR_mincore] = "__NR_mincore";
#endif
#ifdef __NR_madvise
  syscall_name[__NR_madvise] = "__NR_madvise";
#endif
#ifdef __NR_getdents64
  syscall_name[__NR_getdents64] = "__NR_getdents64";
#endif
#ifdef __NR_fcntl64
  syscall_name[__NR_fcntl64] = "__NR_fcntl64";
#endif
#ifdef __NR_gettid
  syscall_name[__NR_gettid] = "__NR_gettid";
#endif
#ifdef __NR_readahead
  syscall_name[__NR_readahead] = "__NR_readahead";
#endif
#ifdef __NR_setxattr
  syscall_name[__NR_setxattr] = "__NR_setxattr";
#endif
#ifdef __NR_lsetxattr
  syscall_name[__NR_lsetxattr] = "__NR_lsetxattr";
#endif
#ifdef __NR_fsetxattr
  syscall_name[__NR_fsetxattr] = "__NR_fsetxattr";
#endif
#ifdef __NR_getxattr
  syscall_name[__NR_getxattr] = "__NR_getxattr";
#endif
#ifdef __NR_lgetxattr
  syscall_name[__NR_lgetxattr] = "__NR_lgetxattr";
#endif
#ifdef __NR_fgetxattr
  syscall_name[__NR_fgetxattr] = "__NR_fgetxattr";
#endif
#ifdef __NR_listxattr
  syscall_name[__NR_listxattr] = "__NR_listxattr";
#endif
#ifdef __NR_llistxattr
  syscall_name[__NR_llistxattr] = "__NR_llistxattr";
#endif
#ifdef __NR_flistxattr
  syscall_name[__NR_flistxattr] = "__NR_flistxattr";
#endif
#ifdef __NR_removexattr
  syscall_name[__NR_removexattr] = "__NR_removexattr";
#endif
#ifdef __NR_lremovexattr
  syscall_name[__NR_lremovexattr] = "__NR_lremovexattr";
#endif
#ifdef __NR_fremovexattr
  syscall_name[__NR_fremovexattr] = "__NR_fremovexattr";
#endif
#ifdef __NR_tkill
  syscall_name[__NR_tkill] = "__NR_tkill";
#endif
#ifdef __NR_sendfile64
  syscall_name[__NR_sendfile64] = "__NR_sendfile64";
#endif
#ifdef __NR_futex
  syscall_name[__NR_futex] = "__NR_futex";
#endif
#ifdef __NR_sched_setaffinity
  syscall_name[__NR_sched_setaffinity] = "__NR_sched_setaffinity";
#endif
#ifdef __NR_sched_getaffinity
  syscall_name[__NR_sched_getaffinity] = "__NR_sched_getaffinity";
#endif
#ifdef __NR_set_thread_area
  syscall_name[__NR_set_thread_area] = "__NR_set_thread_area";
#endif
#ifdef __NR_get_thread_area
  syscall_name[__NR_get_thread_area] = "__NR_get_thread_area";
#endif
#ifdef __NR_io_setup
  syscall_name[__NR_io_setup] = "__NR_io_setup";
#endif
#ifdef __NR_io_destroy
  syscall_name[__NR_io_destroy] = "__NR_io_destroy";
#endif
#ifdef __NR_io_getevents
  syscall_name[__NR_io_getevents] = "__NR_io_getevents";
#endif
#ifdef __NR_io_submit
  syscall_name[__NR_io_submit] = "__NR_io_submit";
#endif
#ifdef __NR_io_cancel
  syscall_name[__NR_io_cancel] = "__NR_io_cancel";
#endif
#ifdef __NR_fadvise64
  syscall_name[__NR_fadvise64] = "__NR_fadvise64";
#endif
#ifdef __NR_exit_group
  syscall_name[__NR_exit_group] = "__NR_exit_group";
#endif
#ifdef __NR_lookup_dcookie
  syscall_name[__NR_lookup_dcookie] = "__NR_lookup_dcookie";
#endif
#ifdef __NR_epoll_create
  syscall_name[__NR_epoll_create] = "__NR_epoll_create";
#endif
#ifdef __NR_epoll_ctl
  syscall_name[__NR_epoll_ctl] = "__NR_epoll_ctl";
#endif
#ifdef __NR_epoll_wait
  syscall_name[__NR_epoll_wait] = "__NR_epoll_wait";
#endif
#ifdef __NR_remap_file_pages
  syscall_name[__NR_remap_file_pages] = "__NR_remap_file_pages";
#endif
#ifdef __NR_set_tid_address
  syscall_name[__NR_set_tid_address] = "__NR_set_tid_address";
#endif
#ifdef __NR_timer_create
  syscall_name[__NR_timer_create] = "__NR_timer_create";
#endif
#ifdef __NR_timer_settime
  syscall_name[__NR_timer_settime] = "__NR_timer_settime";
#endif
#ifdef __NR_timer_gettime
  syscall_name[__NR_timer_gettime] = "__NR_timer_gettime";
#endif
#ifdef __NR_timer_getoverrun
  syscall_name[__NR_timer_getoverrun] = "__NR_timer_getoverrun";
#endif
#ifdef __NR_timer_delete
  syscall_name[__NR_timer_delete] = "__NR_timer_delete";
#endif
#ifdef __NR_clock_settime
  syscall_name[__NR_clock_settime] = "__NR_clock_settime";
#endif
#ifdef __NR_clock_gettime
  syscall_name[__NR_clock_gettime] = "__NR_clock_gettime";
#endif
#ifdef __NR_clock_getres
  syscall_name[__NR_clock_getres] = "__NR_clock_getres";
#endif
#ifdef __NR_clock_nanosleep
  syscall_name[__NR_clock_nanosleep] = "__NR_clock_nanosleep";
#endif
#ifdef __NR_statfs64
  syscall_name[__NR_statfs64] = "__NR_statfs64";
#endif
#ifdef __NR_fstatfs64
  syscall_name[__NR_fstatfs64] = "__NR_fstatfs64";
#endif
#ifdef __NR_tgkill
  syscall_name[__NR_tgkill] = "__NR_tgkill";
#endif
#ifdef __NR_utimes
  syscall_name[__NR_utimes] = "__NR_utimes";
#endif
#ifdef __NR_fadvise64_64
  syscall_name[__NR_fadvise64_64] = "__NR_fadvise64_64";
#endif
#ifdef __NR_vserver
  syscall_name[__NR_vserver] = "__NR_vserver";
#endif
#ifdef __NR_mbind
  syscall_name[__NR_mbind] = "__NR_mbind";
#endif
#ifdef __NR_get_mempolicy
  syscall_name[__NR_get_mempolicy] = "__NR_get_mempolicy";
#endif
#ifdef __NR_set_mempolicy
  syscall_name[__NR_set_mempolicy] = "__NR_set_mempolicy";
#endif
#ifdef __NR_mq_open
  syscall_name[__NR_mq_open] = "__NR_mq_open";
#endif
#ifdef __NR_mq_unlink
  syscall_name[__NR_mq_unlink] = "__NR_mq_unlink";
#endif
#ifdef __NR_mq_timedsend
  syscall_name[__NR_mq_timedsend] = "__NR_mq_timedsend";
#endif
#ifdef __NR_mq_timedreceive
  syscall_name[__NR_mq_timedreceive] = "__NR_mq_timedreceive";
#endif
#ifdef __NR_mq_notify
  syscall_name[__NR_mq_notify] = "__NR_mq_notify";
#endif
#ifdef __NR_mq_getsetattr
  syscall_name[__NR_mq_getsetattr] = "__NR_mq_getsetattr";
#endif
#ifdef __NR_kexec_load
  syscall_name[__NR_kexec_load] = "__NR_kexec_load";
#endif
#ifdef __NR_waitid
  syscall_name[__NR_waitid] = "__NR_waitid";
#endif
#ifdef __NR_add_key
  syscall_name[__NR_add_key] = "__NR_add_key";
#endif
#ifdef __NR_request_key
  syscall_name[__NR_request_key] = "__NR_request_key";
#endif
#ifdef __NR_keyctl
  syscall_name[__NR_keyctl] = "__NR_keyctl";
#endif
#ifdef __NR_ioprio_set
  syscall_name[__NR_ioprio_set] = "__NR_ioprio_set";
#endif
#ifdef __NR_ioprio_get
  syscall_name[__NR_ioprio_get] = "__NR_ioprio_get";
#endif
#ifdef __NR_inotify_init
  syscall_name[__NR_inotify_init] = "__NR_inotify_init";
#endif
#ifdef __NR_inotify_add_watch
  syscall_name[__NR_inotify_add_watch] = "__NR_inotify_add_watch";
#endif
#ifdef __NR_inotify_rm_watch
  syscall_name[__NR_inotify_rm_watch] = "__NR_inotify_rm_watch";
#endif
#ifdef __NR_migrate_pages
  syscall_name[__NR_migrate_pages] = "__NR_migrate_pages";
#endif
#ifdef __NR_openat
  syscall_name[__NR_openat] = "__NR_openat";
#endif
#ifdef __NR_mkdirat
  syscall_name[__NR_mkdirat] = "__NR_mkdirat";
#endif
#ifdef __NR_mknodat
  syscall_name[__NR_mknodat] = "__NR_mknodat";
#endif
#ifdef __NR_fchownat
  syscall_name[__NR_fchownat] = "__NR_fchownat";
#endif
#ifdef __NR_futimesat
  syscall_name[__NR_futimesat] = "__NR_futimesat";
#endif
#ifdef __NR_fstatat64
  syscall_name[__NR_fstatat64] = "__NR_fstatat64";
#endif
#ifdef __NR_unlinkat
  syscall_name[__NR_unlinkat] = "__NR_unlinkat";
#endif
#ifdef __NR_renameat
  syscall_name[__NR_renameat] = "__NR_renameat";
#endif
#ifdef __NR_linkat
  syscall_name[__NR_linkat] = "__NR_linkat";
#endif
#ifdef __NR_symlinkat
  syscall_name[__NR_symlinkat] = "__NR_symlinkat";
#endif
#ifdef __NR_readlinkat
  syscall_name[__NR_readlinkat] = "__NR_readlinkat";
#endif
#ifdef __NR_fchmodat
  syscall_name[__NR_fchmodat] = "__NR_fchmodat";
#endif
#ifdef __NR_faccessat
  syscall_name[__NR_faccessat] = "__NR_faccessat";
#endif
#ifdef __NR_pselect6
  syscall_name[__NR_pselect6] = "__NR_pselect6";
#endif
#ifdef __NR_ppoll
  syscall_name[__NR_ppoll] = "__NR_ppoll";
#endif
#ifdef __NR_unshare
  syscall_name[__NR_unshare] = "__NR_unshare";
#endif
#ifdef __NR_set_robust_list
  syscall_name[__NR_set_robust_list] = "__NR_set_robust_list";
#endif
#ifdef __NR_get_robust_list
  syscall_name[__NR_get_robust_list] = "__NR_get_robust_list";
#endif
#ifdef __NR_splice
  syscall_name[__NR_splice] = "__NR_splice";
#endif
#ifdef __NR_sync_file_range
  syscall_name[__NR_sync_file_range] = "__NR_sync_file_range";
#endif
#ifdef __NR_tee
  syscall_name[__NR_tee] = "__NR_tee";
#endif
#ifdef __NR_vmsplice
  syscall_name[__NR_vmsplice] = "__NR_vmsplice";
#endif
#ifdef __NR_move_pages
  syscall_name[__NR_move_pages] = "__NR_move_pages";
#endif
#ifdef __NR_getcpu
  syscall_name[__NR_getcpu] = "__NR_getcpu";
#endif
#ifdef __NR_epoll_pwait
  syscall_name[__NR_epoll_pwait] = "__NR_epoll_pwait";
#endif
#ifdef __NR_utimensat
  syscall_name[__NR_utimensat] = "__NR_utimensat";
#endif
#ifdef __NR_signalfd
  syscall_name[__NR_signalfd] = "__NR_signalfd";
#endif
#ifdef __NR_timerfd_create
  syscall_name[__NR_timerfd_create] = "__NR_timerfd_create";
#endif
#ifdef __NR_eventfd
  syscall_name[__NR_eventfd] = "__NR_eventfd";
#endif
#ifdef __NR_fallocate
  syscall_name[__NR_fallocate] = "__NR_fallocate";
#endif
#ifdef __NR_timerfd_settime
  syscall_name[__NR_timerfd_settime] = "__NR_timerfd_settime";
#endif
#ifdef __NR_timerfd_gettime
  syscall_name[__NR_timerfd_gettime] = "__NR_timerfd_gettime";
#endif
#ifdef __NR_signalfd4
  syscall_name[__NR_signalfd4] = "__NR_signalfd4";
#endif
#ifdef __NR_eventfd2
  syscall_name[__NR_eventfd2] = "__NR_eventfd2";
#endif
#ifdef __NR_epoll_create1
  syscall_name[__NR_epoll_create1] = "__NR_epoll_create1";
#endif
#ifdef __NR_dup3
  syscall_name[__NR_dup3] = "__NR_dup3";
#endif
#ifdef __NR_pipe2
  syscall_name[__NR_pipe2] = "__NR_pipe2";
#endif
#ifdef __NR_inotify_init1
  syscall_name[__NR_inotify_init1] = "__NR_inotify_init1";
#endif
#ifdef __NR_preadv
  syscall_name[__NR_preadv] = "__NR_preadv";
#endif
#ifdef __NR_pwritev
  syscall_name[__NR_pwritev] = "__NR_pwritev";
#endif
#ifdef __NR_rt_tgsigqueueinfo
  syscall_name[__NR_rt_tgsigqueueinfo] = "__NR_rt_tgsigqueueinfo";
#endif
#ifdef __NR_perf_event_open
  syscall_name[__NR_perf_event_open] = "__NR_perf_event_open";
#endif
#ifdef __NR_recvmmsg
  syscall_name[__NR_recvmmsg] = "__NR_recvmmsg";
#endif
#ifdef __NR_fanotify_init
  syscall_name[__NR_fanotify_init] = "__NR_fanotify_init";
#endif
#ifdef __NR_fanotify_mark
  syscall_name[__NR_fanotify_mark] = "__NR_fanotify_mark";
#endif
#ifdef __NR_prlimit64
  syscall_name[__NR_prlimit64] = "__NR_prlimit64";
#endif
#ifdef __NR_name_to_handle_at
  syscall_name[__NR_name_to_handle_at] = "__NR_name_to_handle_at";
#endif
#ifdef __NR_open_by_handle_at
  syscall_name[__NR_open_by_handle_at] = "__NR_open_by_handle_at";
#endif
#ifdef __NR_clock_adjtime
  syscall_name[__NR_clock_adjtime] = "__NR_clock_adjtime";
#endif
#ifdef __NR_syncfs
  syscall_name[__NR_syncfs] = "__NR_syncfs";
#endif
#ifdef __NR_sendmmsg
  syscall_name[__NR_sendmmsg] = "__NR_sendmmsg";
#endif
#ifdef __NR_setns
  syscall_name[__NR_setns] = "__NR_setns";
#endif
#ifdef __NR_process_vm_readv
  syscall_name[__NR_process_vm_readv] = "__NR_process_vm_readv";
#endif
#ifdef __NR_process_vm_writev
  syscall_name[__NR_process_vm_writev] = "__NR_process_vm_writev";
#endif
#ifdef __NR_kcmp
  syscall_name[__NR_kcmp] = "__NR_kcmp";
#endif
#ifdef __NR_finit_module
  syscall_name[__NR_finit_module] = "__NR_finit_module";
#endif

  return 0;

}	// end of init_table()

int t = init_syscall_table();

#endif	/* SYSCALLS_H */

