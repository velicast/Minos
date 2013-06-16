package com.minos.onlinejudge.service

import com.minos.onlinejudge.domain.Contest

class ContestService {

  def isRegistered(Contest contest, user) {
    contest.findByUserAndContest(user, contest) != null
  }
}
