package com.minos.onlinejudge.service

import com.minos.onlinejudge.domain.Contestant

class ContestService {

  def isRegistered(contest, user) {
    return Contestant.findByUserAndContest(user, contest) != null
  }
}