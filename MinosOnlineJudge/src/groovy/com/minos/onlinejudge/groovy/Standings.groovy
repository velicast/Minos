/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minos.onlinejudge.groovy;

import com.minos.onlinejudge.domain.Contest
import com.minos.onlinejudge.domain.Problem
import com.minos.onlinejudge.domain.Contestant
import com.minos.onlinejudge.domain.Submission
import com.minos.onlinejudge.Minos

/**
 *
 * @author Eduar Castrillo (eduarc)
 */
class Standings {
    // 20 mins de penalty por envi'o incorrecto
  static final PENALTY_TIME = 20 * 60 * 1000
  
  def problemIdentifiers
  def problems
  def contestants
  def positions
  def contest
  long startTime
  
  public Standings(Contest contest) {
    
    problems    = contest.problems
    contestants = contest.contestants
    positions = new Row[contestants.size()]
    problemIdentifiers = new char[problems.size()]
    
    int i = 0;
    for (Problem p : problems) {
      problemIdentifiers[i++] = p.alphabet
    }
    Arrays.sort(problemIdentifiers)
    
    Calendar cal = Calendar.getInstance()
    cal.setTime(contest.startTime)
    startTime = cal.getTimeInMillis()
    
    i = 0
    for (Contestant c : contestants) {
      positions[i++] = new Row(c)
    }
    Arrays.sort(positions)
    
    def currPos = 1
    positions[0].position = 1
    for (i = 1; i < positions.length; ++i) {
      Row r1 = positions[i]
      Row r2 = positions[i - 1]
      if (r1.solvedProblems != r2.solvedProblems
        || r1.penaltyTime != r2.penaltyTime) {
        ++currPos
      }
      positions[i].position = currPos
    }
  }
  
  /* Retorna la columna del problema */
  public int getColumn(Problem p) {
    
    for (int i = 0; i < problemIdentifiers.length; ++i) {
      if (problemIdentifiers[i] == p.alphabet) {
        return i
      }
    }
    return -1
  }
  
  /* Retorna la ubicacion del competidor en el Standing */
  public int getRow(Contestant c) {
    
    for (int i = 0; i < positions.length; ++i) {
      if (c.user.id == positions[i].target.user.id) {
        return id
      }
    }
    return -1
  }
  
  /* Representacion de un Concursante en este Standings */
  class Row implements Comparable<Row> {
    
    long penaltyTime
    int solvedProblems
    def accumulatedTime
    def attempts
    int position
    def solvedTime
    Contestant target
    
    public Row(Contestant cont) {
      
      target = cont
      attempts = new int[problems.size()]
      solvedTime = new int[problems.size()]
      accumulatedTime = new int[problems.size()]
      solvedProblems = 0
      penaltyTime = 0
      
      Calendar aux = Calendar.getInstance()
    
      def arr = new Submission[target.submissions.size()]
      int i = 0
      for (Submission s : target.submissions) {
        arr[i++] = s
      }
      Arrays.sort(arr)
      
      for (i = 0; i < arr.length; ++i) {
        Submission s = arr[i]
        int st = s.status
        Problem p = s.problem
        int col = getColumn(p)
        
        if (solvedTime[col] == 0 && st != Minos.DENIAL_OF_JUDGEMENT) {
          attempts[col]++
        }
        
        if (st == Minos.ACCEPTED && solvedTime[col] == 0) {
          ++solvedProblems
          
          aux.setTime(s.date)
          
          int elapsed = aux.getTimeInMillis() - startTime
          solvedTime[col] = elapsed / (1000 * 60)
          penaltyTime += elapsed + accumulatedTime[col]
        }
        else if (st != Minos.DENIAL_OF_JUDGEMENT && st != Minos.ACCEPTED) {
          accumulatedTime[col] += PENALTY_TIME
        }
      }
      penaltyTime /= 1000 * 60
    }
    
    public int compareTo(Row o) {
      
      if (solvedProblems > o.solvedProblems) return -1
      if (solvedProblems < o.solvedProblems) return 1
      if (penaltyTime < o.penaltyTime) return -1
      if (penaltyTime > o.penaltyTime) return 1
      int c = target.user.username.compareTo(o.target.user.username)
      if (c < 0) return -1
      if (c > 0) return 1
      return 0
    }
  }
}

