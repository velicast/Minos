package com.minos.onlinejudge.controller

import com.minos.onlinejudge.ContestTimeTracer
import com.minos.onlinejudge.domain.Contest
import com.minos.onlinejudge.domain.ContestAdminister

class ContestAdministerController {

  def index() {
        // So'lo usuarios logeados
    if (session.user == null || session.user.role != "admin") {
      redirect(controller: "user", action: "login")
      return
    }
    
    def contests = Contest.list()
    
    contests.sort { a, b ->
      if (a.startTime.after(b.startTime)) return -1
      return 1
    }
    [contestList: contests, contestTotal: Contest.count()]
  }
  
  def edit() {
    if (session.user == null || session.user.role != "admin") {
      redirect(controller: "user", action: "login")
      return
    }
    
    Contest contest = Contest.get(params.contestID)
    
    [contest: contest]
  }
  
  def update() {
    Contest contest = Contest.get(params.contestID)
    
    def title = params.title
    Date starttime = Date.parse("yyyy/MM/dd H:m", params.starttime)
    Date endtime = Date.parse("yyyy/MM/dd H:m", params.endtime)
    
    contest.title = title
    contest.startTime = starttime
    contest.endTime = endtime
    contest.save()
    
    redirect(controller: "contestAdminister", action: "edit", params: [contestID: contest.id])
  }
  
  def create() {
          // So'lo usuarios logeados
    if (session.user == null || session.user.role != "admin") {
      redirect(controller: "user", action: "login")
      return
    }
  }
  
  def docreate() {
    def title = params.title
    Date starttime = Date.parse("yyyy/MM/dd H:m", params.starttime)
    Date endtime = Date.parse("yyyy/MM/dd H:m", params.endtime)
    
    Contest contest = new Contest()
    contest.title = title
    contest.startTime = starttime
    contest.endTime = endtime
    
    contest.save()
    ContestTimeTracer.trace(contest)
    
    redirect(controller: "contestAdminister", action: "index")
  }
  
  def problems() {
       // So'lo usuarios logeados
    if (session.user == null || session.user.role != "admin") {
      redirect(controller: "user", action: "login")
      return
    }
    
    Contest contest = Contest.get(params.contestID)
    
    [problemList: contest.problems.sort(), contest: contest]
  }
}
