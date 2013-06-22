/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minos.onlinejudge;

import com.minos.onlinejudge.domain.Contest;
import com.minos.onlinejudge.service.ContestService;
import java.util.Calendar;

import java.util.LinkedList;
import java.util.Queue;
    
/**
 *
 * @author Eduar Castrillo (eduarc)
 */
public class ContestTimeTracer {
  
  private static Queue<ContestTimeTracer.Tracer> contestQueue;
  private static ContestService toNotify;
  
  private static ContestTimeTracer defaultTracer = new ContestTimeTracer();
  
  private ContestTimeTracer() {
    
    contestQueue = new LinkedList();
  }
  
  public static ContestTimeTracer getDefaultTracer() {
    return defaultTracer;
  }
  
  public static void trace(Contest contest) {
    
    ContestTimeTracer.Tracer task = new ContestTimeTracer.Tracer(contest);
    contestQueue.add(task);
    task.start();
  }
  
  public static void setNotificationTarget(ContestService target) {
    toNotify = target;
  }

  /* Notificar cambio en el envi'o src al servicio respectivo */
  private static void notifyChange(Contest src) {
    toNotify.stateChanged(src);
  }
  
  private static class Tracer extends Thread {
    
    Contest target;
    
    public Tracer(Contest t) {
      
      target = t;
      setPriority(Thread.MAX_PRIORITY);
    }
    
    @Override
    public void run() {
      
      while (target.getStatus() != Contest.ST_FINISHED) {
        
        Calendar now = Calendar.getInstance();
        
        Calendar start = Calendar.getInstance();
        start.setTime(target.getStartTime());
        
        Calendar end = Calendar.getInstance();
        end.setTime(target.getEndTime());
        
          // inicio
        if (target.getStatus() == Contest.ST_CREATED && now.after(start)) {
          target.setStatus(Contest.ST_RUNNING);
          notifyChange(target);
        }
          // fin
        else if (target.getStatus() == Contest.ST_RUNNING && now.after(end)) {
          target.setStatus(Contest.ST_FINISHED);
          notifyChange(target);
        }
        
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
          // LOG Error here!!!
        }
      }
      contestQueue.remove(this);
    }
  }
}
