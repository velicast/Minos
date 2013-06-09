package com.minos.onlinejudge.domain

class ContestAdminister {
  User user
  Contest contest = []

  static hasMany = [contest:Contest]
  
  static constraints = {}
}