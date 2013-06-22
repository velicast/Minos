package com.minos.onlinejudge.domain

class Clarification implements Comparable<Clarification> {
  String question
  String answer 
  String problem
  Date date
  Contest contest
  User user
  
  public Clarification() {
    problem = ""
    question = ""
    answer = ""
    date = new Date()
  }
  
  static belongsTo = [contest:Contest, user:User]

  static constraints = {
    question(blank:false)
  }
  
  public int compareTo(Clarification c) {
    
    if (date.after(c.date)) return -1
    return 1
  }
}