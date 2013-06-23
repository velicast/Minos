package com.minos.onlinejudge.controller

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
    Date starttime = Date.parse("dd/MM/yyyy H:m", params.starttime)
    Date endtime = Date.parse("dd/MM/yyyy H:m", params.endtime)
    
    Contest contest = new Contest()
    contest.title = title
    contest.startTime = starttime
    contest.endTime = endtime
    
    contest.save()
    redirect(controller: "contestAdminister", action: "index")
  }
}
