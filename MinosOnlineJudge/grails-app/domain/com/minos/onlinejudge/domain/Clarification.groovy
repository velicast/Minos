package com.minos.onlinejudge.domain

class Clarification {
  String question
  String answer 
  Date date
  Contest contest
  User user
  
  static belongsTo = [contest:Contest, user:User]

  static constraints = {
    question(blank:false)
  }
}