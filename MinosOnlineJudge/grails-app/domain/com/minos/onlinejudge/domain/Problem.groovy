package com.minos.onlinejudge.domain

class Problem {
  
  static final DEFAULT_CHECKER = 0
  static final SPECIAL_CHECKER = 1
  
  String title
  String rootPath
  Integer memoryLimit
  Integer timeLimit
  Integer stackLimit
  Integer outputLimit
  Integer numberOfTest
  Integer typeChecker
  String tokenSeparators
  String statement
  User uploader
  String author
  Contest contest

  public Problem() {
    
    title = ""
    rootPath = ""
    memoryLimit = 0
    timeLimit = 0
    stackLimit = 0
    outputLimit = 0
    numberOfTest = 0
    typeChecker = DEFAULT_CHECKER
    tokenSeparators = "\0"
    statement = ""
  }
  
  static hasMany = [solutions:Submission]
  static belongsTo = [contest:Contest]

  static constraints = {
      title(blank:false)
      memoryLimit(min:32768, max:524288)
      timeLimit(min:500, max:100000)
      stackLimit(min:32768, max:524288)
      outputLimit(min:5, max:4096)
      numberOfTest(min:1, max:512)
      typeChecker(blank:false, min:0, max:1)
      tokenSeparators()
      statement(blank:false)
  }
}