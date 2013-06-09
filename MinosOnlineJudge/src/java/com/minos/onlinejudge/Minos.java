package com.minos.onlinejudge;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.minos.onlinejudge.domain.Submission;
import com.minos.onlinejudge.domain.Problem;
import com.minos.onlinejudge.service.SubmissionService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Todos los valores relacionados a la memoria RAM esta'n dados en unidades de
 * KiloBytes (KB) Todos los valores relacionados a el tiempo de ejecucion esta'n
 * dados en unidades de milisegundos (KB)
 *
 * se usa la correspondencia 1 KiloByte = 1024 bytes
 *
 * @author Eduar Castrillo (eduarc)
 */
public class Minos {

  public static final String ROOT_DIR       = "/home/arch/Minos/root";  // depende del computador
  public static final String COMPILE_DIR    = ROOT_DIR + "/compile";
  public static final String SANDBOX_DIR    = ROOT_DIR + "/sandbox";
  public static final String CHECKER_DIR    = ROOT_DIR + "/checker";
  public static final String PROBLEM_DIR    = ROOT_DIR + "/problem";
  public static final String SUBMISSION_DIR = ROOT_DIR + "/submission";
  
   /* Possible status from a Sandbox */
  public static final int SUCCESS                = 0;
  public static final int INTERNAL_ERROR         = 1;
  public static final int RUNTIME_ERROR          = 2;
  public static final int TIME_LIMIT_EXCEEDED    = 3;
  public static final int MEMORY_LIMIT_EXCEEDED  = 4;
  public static final int OUTPUT_LIMIT_EXCEEDED  = 5;
  public static final int ILLEGAL_INSTRUCTION    = 6;
  
  /* Possible status from a checker */
  public static final int ACCEPTED     = 7;
  public static final int WRONG_ANSWER = 8;
  
  /* Other status */
  public static final int COMPILATION_ERROR   = 9;
  public static final int COMPILING           = 10;
  public static final int RUNNING             = 11;
  public static final int JUDGING             = 12;
  public static final int IN_QUEUE            = 13;
  public static final int DENIAL_OF_JUDGEMENT = 14;
          
  public static final int CPP_LANG  = 0;
  public static final int JAVA_LANG = 1;
  
  public static final String STATUS_NAME[] = new String[16];
  public static final String LANG_NAME[] = new String[2];
  
  /* Memoria que no puede ser usada por el minos. Reservada para la ejecucion de la aplicacion */
  public static final long SYSTEM_BASE_MEMORY = 32768;
  /* Instancia u'nica y por defecto del Sistema. Singleton */
  private static Minos defaultJudge = new Minos();
  /* Cola de tareas esperando a ser ejecutadas */
  private static Queue<Minos.Task> judgeQueue;
  /* Lista de tareas que esta'n siendo ejecutadas */
  private static List<Minos.Task> runningTasks;
  /* Memoria que ha sido asignada a las tareas que esta'n siendo ejecutadas */
  private static volatile long allocatedMemory;
  private static OperatingSystemMXBean OSBean;
  
  /* Servicio notificado cuand un Submission cambia de estado */
  private static SubmissionService toNotify;

  private Minos() {

    judgeQueue = new LinkedList();
    runningTasks = new ArrayList();

    OSBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    allocatedMemory = 0;
    
    STATUS_NAME[COMPILATION_ERROR] = "Compilation Error";
    STATUS_NAME[INTERNAL_ERROR] = "Internal Error";
    STATUS_NAME[SUCCESS] = "Success";
    
    STATUS_NAME[RUNTIME_ERROR] = "Runtime Error";
    STATUS_NAME[MEMORY_LIMIT_EXCEEDED] = "Memory Limit Exceeded";
    STATUS_NAME[OUTPUT_LIMIT_EXCEEDED] = "Output Limit Exceeded";
    STATUS_NAME[TIME_LIMIT_EXCEEDED] = "Time Limit Exceeded";
    STATUS_NAME[ILLEGAL_INSTRUCTION] = "Illegal instruction";
    STATUS_NAME[DENIAL_OF_JUDGEMENT] = "Denial of judgement";
    STATUS_NAME[ACCEPTED] = "Accepted";
    STATUS_NAME[WRONG_ANSWER] = "Wrong Answer";
    
    STATUS_NAME[COMPILING] = "Compiling";
    STATUS_NAME[RUNNING] = "Running";
    STATUS_NAME[JUDGING] = "Judging";
    STATUS_NAME[IN_QUEUE] = "In queue";
    
    LANG_NAME[CPP_LANG] = "GNU C++";
    LANG_NAME[JAVA_LANG] = "Oracle Java";
  }

  /* Retorna la unica instancia del juez */
  public static Minos getDefaultJudge() {
    return defaultJudge;
  }

  public static void setNotificationTarget(SubmissionService target) {
    toNotify = target;
  }

  /* Notificar cambio en el envi'o src al servicio respectivo */
  private static void notifyChange(Submission src) {
    toNotify.stateChanged(src);
  }

  /* 
   * Agrega un nuevo envi'o a la cola del sistema, para esperar a ser juzgado.
   * 
   * @return true en todos los casos
   */
  public static void judge(Submission submission) {

    submission.setStatus(Minos.IN_QUEUE);
    notifyChange(submission);
    judgeQueue.add(new Minos.Task(submission));
    processNextTask();
  }

  /* 
   * Procesa la siguiente tarea en cola.
   * Si la tarea puede ser procesada, osea, se cuenta con los recursos suficientes para
   * hacerlo, se ejecuta.Si la tarea no puede ser ejecutada ninguna circunstancia, se le
   * asigna el estado "DENIAL_OF_JUDGEMENT" al envi'o y se descarta.
   * 
   * @returns true si se ejecuta la siguiente tarea en cola; false en caso contrario
   */
  private static synchronized boolean processNextTask() {
    
    int qSize = judgeQueue.size();
    // no hay mas tareas para procesar
    if (qSize == 0) {
      return true;
    }
    
    while (judgeQueue.size() > 0) {
      
      long available = (OSBean.getFreePhysicalMemorySize() / 1024) - Minos.SYSTEM_BASE_MEMORY;

      Minos.Task nextTask = judgeQueue.peek();
      Submission target = nextTask.getTarget();

      long expectedMemory = target.getProblem().getMemoryLimit();
        // hay recursos disponibles
      if (available - allocatedMemory > expectedMemory) {
        judgeQueue.remove();
        allocatedMemory += expectedMemory;  // reservar memoria
        nextTask.start();
        continue;
      } // El sistema no cuenta con los recursos necesarios para ejecutar esta tarea
      else if (runningTasks.isEmpty()) {
        judgeQueue.remove();
        target.setStatus(Minos.DENIAL_OF_JUDGEMENT);
        notifyChange(target);
        continue;
      }
      return false;
    }
    return false;
  }

  /*
   * Unidad de trabajo del Juez. Realiza todo el proceso de juzgamiento de
   * de una solucion.
   */
  private static class Task extends Thread {

    private Submission target;

    public Task(Submission t) {
      target = t;
      setPriority(Thread.MAX_PRIORITY);
    }

    /* Retorna el envi'o relacionado a esta tarea */
    public Submission getTarget() {
      return target;
    }

    /* Compila el codigo fuente */
    private void compile() {
      
      target.setStatus(Minos.COMPILING);
      notifyChange(target);
    
      Process process = null;
      String fName = target.getFileName();
      
      if (target.getLanguage() == Minos.CPP_LANG) {
        String execName = fName.substring(0, fName.length() - 4);
        String script = Minos.COMPILE_DIR + "/cpp.sh " + fName + " " + execName;

        try {
          process = Runtime.getRuntime().exec(script, null, new File(target.getRootPath()));
        } catch (IOException ex) {
            // LOG error here!
          target.setStatus(Minos.INTERNAL_ERROR);
          notifyChange(target);
          return;
        }
      }
      else if (target.getLanguage() == Minos.JAVA_LANG) {
        String script = Minos.COMPILE_DIR + "/java.sh " + fName + " " + target.getRootPath();
        
        try {
          process = Runtime.getRuntime().exec(script, null, new File(target.getRootPath()));
        } catch (IOException ex) {
            // LOG error here!
          target.setStatus(Minos.INTERNAL_ERROR);
          notifyChange(target);
        }
      }
        // wait to finish compilation
      try {
        int res = process.waitFor();
        if (res != Minos.SUCCESS) {
          target.setStatus(Minos.COMPILATION_ERROR);
          notifyChange(target);
        }
      } catch (InterruptedException ex) {
          // LOG error here!
        target.setStatus(Minos.INTERNAL_ERROR);
        notifyChange(target);
      }
    }

    /* Ejecuta el programa generado en el proceso de compilacion */
    private void execute() {
      
      target.setStatus(Minos.RUNNING);
      notifyChange(target);
    
      Process process = null;
      int time = 0, memory = 0;

        // create output dir
      File outputDir = new File(target.getRootPath() + "/output");
      if (!outputDir.exists()) {
        outputDir.mkdir();
      }
      
      Problem problem = target.getProblem();
      int nTests = problem.getNumberOfTest();
      String outFile = target.getRootPath() + "/output/out";
      String fName = target.getFileName();

      String execName = "", cmd = "", limits = "";
      
      int lang = target.getLanguage();
      
      if (lang == Minos.CPP_LANG) {
        execName = fName.substring(0, fName.length() - 4);
        cmd = Minos.SANDBOX_DIR + "/cppsandbox " + problem.getRootPath() + "/input/";
        limits = problem.getTimeLimit() + " " + problem.getMemoryLimit() + " " +  problem.getStackLimit() + " " +
                        problem.getOutputLimit() + " " + target.getRootPath() + "/" + execName;
      }
      else if (lang == Minos.JAVA_LANG) {
        execName = fName.substring(0, fName.length() - 5) + ".class";
        cmd = "java -jar " + Minos.SANDBOX_DIR + "/javasandbox.jar " + problem.getRootPath() + "/input/";
        limits = problem.getTimeLimit() + " " + problem.getMemoryLimit() + " " + problem.getOutputLimit() + " " + execName;
      }
      
      for (int i = 1; i <= nTests; ++i) {
        File output = new File(outFile + i);
        try {
          output.createNewFile();
        } catch (IOException ex) {
            // LOG error here!
          target.setStatus(Minos.INTERNAL_ERROR);
          notifyChange(target);
          return;
        }
        
        String script = cmd + "in" + i + " " + target.getRootPath() + "/output/out" + i + " " + limits;
        
        try {
          process = Runtime.getRuntime().exec(script, null, new File(target.getRootPath()));
        } catch (IOException ex) {
            // LOG error here!
          target.setStatus(Minos.INTERNAL_ERROR);
          notifyChange(target);
          return;
        }

        // wait to finish execution
        int res = 0;
        try {
          res = process.waitFor();
        } catch (InterruptedException ex) {
            // LOG error here!
          target.setStatus(Minos.INTERNAL_ERROR);
          notifyChange(target);
          return;
        }
        // get result data
        InputStream reader = process.getInputStream();
        byte buff[] = null;
        try {
          buff = new byte[reader.available()];
          reader.read(buff);
        } catch (IOException ex) {
            // LOG error here!
          target.setStatus(Minos.INTERNAL_ERROR);
          notifyChange(target);
          return;
        }
        String stats = new String(buff), sTime = "", sMemory = "";
        
        int j = 0;
        for (; stats.charAt(j) != ' '; ++j) {
          sTime += stats.charAt(j);
        }
        while(stats.charAt(j) == ' ') ++j;
        for (; j < stats.length(); ++j) {
          sMemory += stats.charAt(j);
        }
        
        time = Math.max(atoi(sTime), time);
        memory = Math.max(atoi(sMemory), memory);
        
        if (res != Minos.SUCCESS) {
          target.setStatus(res);
          target.setTimeElapsed(time);
          target.setMemoryUsed(memory);
          notifyChange(target);
          return;
        }

        res = check(i); // calificar ejecucion

        if (res != Minos.ACCEPTED) {
          target.setStatus(res);
          target.setTimeElapsed(time);
          target.setMemoryUsed(memory);
          notifyChange(target);
          return;
        }
      }
      
      target.setStatus(Minos.ACCEPTED);
      target.setTimeElapsed(time);
      target.setMemoryUsed(memory);
      notifyChange(target);
    }

    private int atoi(String s) {
      
      int r = 0, l = s.length();
      for (int i = 0; i < l; ++i) {
        r *= 10;
        r += (int)(s.charAt(i) - '0');
      }
      return r;
    }
    
    /* Llama al checker correpondiente para evaluar la salida */
    private int check(int test) {
      
      String pPath = target.getProblem().getRootPath();
      String cmd[] = null;
      
      if (target.getProblem().getTypeChecker() == 1) {
        cmd = new String[4];
        cmd[0] = pPath + "/checker/checker";
        cmd[1] = pPath + "/input/in" + test;
        cmd[2] = pPath + "/output/out" + test;
        cmd[3] = "out" + test;
      } else {
        cmd = new String[4];
        cmd[0] = Minos.CHECKER_DIR + "/checker";
        cmd[1] = pPath + "/output/out" + test;
        cmd[2] = "out" + test;
        cmd[3] = target.getProblem().getTokenSeparators();
      }
      Process process = null;
      int res = 0;
      try {
        process = Runtime.getRuntime().exec(cmd, null, new File(target.getRootPath() + "/output"));
      } catch (IOException ex) {
          // LOG error here!
        return Minos.INTERNAL_ERROR;
      }
      
        // wait to finish the check and return
      try {
        res = process.waitFor();
      } catch (InterruptedException ex) {
          // LOG error here!
        return Minos.INTERNAL_ERROR;
      }
      return res;
    }

    public void start() {
      
      runningTasks.add(this);
      super.start();
    }

    @Override
    public void run() {
      
      compile();
      if (target.getStatus() == Minos.COMPILING) {
        execute();
      }
      runningTasks.remove(this);
      allocatedMemory -= target.getProblem().getMemoryLimit();
      processNextTask();
    }
  }
}
