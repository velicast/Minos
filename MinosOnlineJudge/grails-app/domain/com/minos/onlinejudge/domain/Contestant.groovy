package com.minos.onlinejudge.domain

class Contestant {
  User user
  Contest contest
  
  static hasMany   = [submissions:Submission]
  static belongsTo = [contest:Contest]
  
  static constraints = {
  }
}
