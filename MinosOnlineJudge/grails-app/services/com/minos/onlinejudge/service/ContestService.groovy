package com.minos.onlinejudge.service

import com.minos.onlinejudge.domain.Contestant

class ContestService {

  def isRegistered(contest, user) {
    Contestant.findByUserAndContest(user, contest) != null
  }
}