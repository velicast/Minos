/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minos.sandbox;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduar Castrillo (eduarc)
 */
public class Sandbox {
  
  private static int memory_limit;
  
  private static int time_limit;
  
  private static int output_limit;

  private static long base_memory_usage = 0;
  
  private static long memory_usage = 0;
  
  private static long written_bytes = 0;
  
  private static long time_elapsed = 0;
  
  private static Method mainMethod;
  
  private static ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
  
  private static MemoryMXBean memoryBean =  ManagementFactory.getMemoryMXBean();
  
  private static DataOutputStream stdout;
  
  private static Thread targetThread = new Thread() {

    @Override
    public void run() {
      
      SSecurityManager securityManager = new SSecurityManager();
      SSecurityManager.targetThread = this;
      System.setSecurityManager(securityManager);
      try {
        time_elapsed = 0;
        mainMethod.invoke(null, (Object) null);
        System.out.close();
      } catch (InvocationTargetException ex) {
        Throwable e = ex.getTargetException();
          // LOG Here!
        if (e instanceof OutOfMemoryError) {
          memory_usage = memory_limit + 1;
          halt(SandboxReply.MEMORY_LIMIT_EXCEEDED);
        }
        else if (e instanceof SecurityException) {
          halt(SandboxReply.ILLEGAL_INSTRUCTION);
        } else {
          halt(SandboxReply.RUNTIME_ERROR);
        }
      } catch (Exception ex) {
        halt(SandboxReply.INTERNAL_ERROR);
      }
    }
  };
  
  /**
   * args[0] = input file
   * args[1] = output file
   * args[2] = time limit
   * args[3] = memory limit
   * args[4] = output limit
   * args[5] = Class files directory
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // save original output stream
      // used to send the result to server
    stdout = new DataOutputStream(System.out);
    
    try {
      threadBean.setThreadCpuTimeEnabled(true);
    } catch(UnsupportedOperationException ex) {
        // LOG here!
      halt(SandboxReply.INTERNAL_ERROR);
    }
    
    if(args.length != 6) {
        // LOG Here!
      halt(SandboxReply.INTERNAL_ERROR);
    }
    
    try {
        // Redirect input, output, err streams
      System.setIn(new FileInputStream(args[0]));
      
      System.setOut(new PrintStream(args[1]) {
        
        @Override
        public void write(int b) {
          ++written_bytes; 
          if(written_bytes > output_limit) {
            halt(SandboxReply.OUTPUT_LIMIT_EXCEEDED);
          }
          super.write(b);
        }
        
        @Override
        public void write(byte b[], int off, int len) {
          written_bytes += len;
          if(written_bytes > output_limit) {
            halt(SandboxReply.OUTPUT_LIMIT_EXCEEDED);
          }
          super.write(b, off, len);
        }
      });
          
      System.setErr(new PrintStream("/dev/null"));
      
    } catch (FileNotFoundException ex) {
      halt(SandboxReply.INTERNAL_ERROR);
    }
    
    Scanner sc = new Scanner(args[2]);
    time_limit = sc.nextInt();
    memory_limit = Integer.parseInt(args[3]) * 1024;
    output_limit = Integer.parseInt(args[4]) * 1024;
    
    SClassLoader classLoader = new SClassLoader();
    
    try {
        // and get the Main method
      File mainFile = new File(args[5]);
        // If not exists then "public" Class was not created
      if (!mainFile.exists()) {
          // LOG here!
        halt(SandboxReply.RUNTIME_ERROR);
      }
      
      Class targetClass = classLoader.findClass(mainFile.getAbsolutePath());
      int modifiers = targetClass.getModifiers();
      if (!Modifier.isPublic(modifiers)) {
          // LOG Here!
        halt(SandboxReply.RUNTIME_ERROR); // No public class found!
      }
      mainMethod = getMain(targetClass);
      
    } catch (ClassNotFoundException ex) {
        // LOG Here!
      halt(SandboxReply.INTERNAL_ERROR);
    } catch (NoSuchMethodException ex) {
        // LOG Here!
      halt(SandboxReply.RUNTIME_ERROR); // No main method found!
    }
    
    // get base memory usage
    System.gc();
    base_memory_usage = memoryBean.getHeapMemoryUsage().getUsed();
    
    targetThread.start();
      // trace thread
    for(;;) {
      ThreadInfo info = threadBean.getThreadInfo(targetThread.getId());
      Thread.State state = Thread.State.TERMINATED;
      if(info != null) {
        state = info.getThreadState();
      }
      
      if(state == Thread.State.NEW 
        || state == Thread.State.RUNNABLE
          || state == Thread.State.TERMINATED) {
        
        updateStatistics();
        
        if(memory_usage > memory_limit) {
            // LOG Here!
          halt(SandboxReply.MEMORY_LIMIT_EXCEEDED);
        }
        if(time_elapsed > time_limit) {
            // LOG Here!
          halt(SandboxReply.TIME_LIMIT_EXCEEDED);
        }
        
        if(state == Thread.State.TERMINATED) {
          break;
        }
      }
      else if(SSecurityManager.targetThread != null) {
          // LOG Here!
        halt(SandboxReply.RUNTIME_ERROR);
      }
      try {
          // sufficient to CPU time update
        targetThread.join(5);
      } catch (InterruptedException ex) {
        break;
      }
    }
      // LOG Here!
    halt(SandboxReply.SUCCESS);
  }
  
  private synchronized static void updateStatistics() {
    
    long mem = memoryBean.getHeapMemoryUsage().getUsed() - base_memory_usage;
    if(mem > memory_usage) {
      memory_usage = mem;
      if(memory_usage > memory_limit) {
        memory_usage = memory_limit + 1024;
      }
    }
    if(targetThread == null) {
      return;
    }
    
    long t = threadBean.getThreadCpuTime(targetThread.getId()) +
             threadBean.getThreadUserTime(targetThread.getId()); // this add must be considered
    t /= 1000000;
    if(t >= 0 && t > time_elapsed && t <= 1000000) {
      time_elapsed = t;
      if(time_elapsed > time_limit) {
        time_elapsed = time_limit + 1;
      }
    }
  } 
  
  private static Method getMain(Class from) throws NoSuchMethodException {
    
    Method method = from.getMethod("main", String[].class);
    
    method.setAccessible(true);
    int modifiers = method.getModifiers();
    
    if(Modifier.isStatic(modifiers)) {
      return method;
    }
      // LOG Here!
    throw new NoSuchMethodException("main");
  }
  
  private static void halt(int st) {
    
    SSecurityManager.targetThread = null;
      // write memory and time usage
    try {
      String s = time_elapsed + " " + (memory_usage / 1024);
      stdout.write(s.getBytes(), 0, s.length());
      stdout.close();
    } catch (IOException ex) {
      // LOG here!
    }
      // LOG Here!
    Runtime.getRuntime().halt(st);
  }
}
