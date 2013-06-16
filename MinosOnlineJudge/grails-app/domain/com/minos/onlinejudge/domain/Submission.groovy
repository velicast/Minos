package com.minos.onlinejudge.domain

import com.minos.onlinejudge.Minos

class Submission implements Comparable<Submission> {
  String fileName
  String rootPath
  Date date
  Integer memoryUsed
  Integer timeElapsed
  Integer status
  Integer language
  Problem problem
  Contestant contestant
    
  public Submission() {
    
    fileName = ""
    rootPath = ""
    memoryUsed = 0
    timeElapsed = 0
    status = Minos.SUCCESS
    language = Minos.CPP_LANG
    date = new Date()
  }
  
  static belongsTo = [problem:Problem, contestant:Contestant]

  static constraints = {
    language(inList:[Minos.CPP_LANG, Minos.JAVA_LANG])
  }
  
  static mapping = {
    version false // Required to avoid stale object exceptions when hibernate attempts a lock
  }
  
  public int compareTo(Submission s) {
    
    if (date.before(s.date)) {
      return -1
    }
    return 1
  }
}

