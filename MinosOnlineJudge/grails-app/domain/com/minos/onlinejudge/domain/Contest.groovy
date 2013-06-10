package com.minos.onlinejudge.domain

class Contest {
  
  static final ST_CREATED  = 0
  static final ST_RUNNING  = 1
  static final ST_FINISHED = 2
  
  Integer status
  String title
  Date startTime
  Date endTime

  public Contest() {
    
    status = ST_CREATED
    title = ""
    startTime = new Date()
    endTime = new Date()
  }
  
  static hasMany = [clarifications:Clarification, problems:Problem, admins:ContestAdminister, contestants:Contestant]
  static belongsTo = [admins:ContestAdminister]

  static constraints = {
    title(blank:false)
    startTime(blank:false)
    endTime(blank:false)
  }
}