package com.minos.onlinejudge.service

import com.minos.onlinejudge.domain.Contestant
import com.minos.onlinejudge.domain.Contest

class ContestService {

  def isRegistered(contest, user) {
    return Contestant.findByUserAndContest(user, contest) != null
  }
  
  void stateChanged(src) {
    
    Contest s = Contest.lock(src.id)
    s.status = src.status
    s.save(flush:true)
  }
}
