package com.minos.onlinejudge.domain

class ContestAdminister {
  User user
  Contest contest

  static belongsTo = [contest:Contest]
  
  static constraints = {}
}