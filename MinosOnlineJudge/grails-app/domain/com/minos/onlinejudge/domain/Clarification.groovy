package com.minos.onlinejudge.domain

class Clarification {
  String question
  String answer 
  Date date
  Contest contest
  User user
  
  public Clarification() {
    
    question = ""
    answer = ""
    date = new Date()
  }
  
  static belongsTo = [contest:Contest, user:User]

  static constraints = {
    question(blank:false)
  }
}