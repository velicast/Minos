package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.Contest

class ContestController {
  
  def index() {
    redirect(action: "list", params: params)
  }
  
  def list(Integer max) {
    if (session.user) {
      params.max = Math.min(max ?: 10, 100)
      [contestList: Contest.list(params), contestTotal: Contest.count()]
    } else {
      redirect(controller:'user')
    }
  }
}
