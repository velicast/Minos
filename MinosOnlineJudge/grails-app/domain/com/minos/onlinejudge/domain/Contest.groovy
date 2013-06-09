package com.minos.onlinejudge.domain

class Contest {
  Integer status
  String title
  Date startTime
  Date endTime

  static hasMany = [clarifications:Clarification, problems:Problem, admins:ContestAdminister, contestants:Contestant]
  static belongsTo = [admins:ContestAdminister]

  static constraints = {
    title(blank:false)
    startTime(blank:false)
    endTime(blank:false)
  }
}