package com.minos.onlinejudge.domain

class Problem implements Comparable<Problem> {
  
  static final DEFAULT_CHECKER = 0
  static final SPECIAL_CHECKER = 1
  
  String title
  Character alphabet
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
    alphabet = 'A'
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
  
  static hasMany = [submissions:Submission]
  static belongsTo = [contest:Contest]

  static constraints = {
      title(blank:false)
      memoryLimit(min:32768, max:524288)
      timeLimit(min:500, max:100000)
      stackLimit(min:32768, max:524288)
      outputLimit(min:5, max:4096)
      numberOfTest(min:1, max:512)
      alphabet()
      typeChecker(blank:false, min:0, max:1)
      tokenSeparators()
      statement(blank:false)
  }
  
  public int compareTo(Problem p) {
    
    if (alphabet < p.alphabet) return -1
    if (alphabet > p.alphabet) return 1
    if (title < p.title) return -1
    if (title > p.title) return 1
    return 0
  }
}