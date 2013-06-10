package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.Contestant

class ContestantController {

  def index() {
    redirect(action: "submissions")
  }
  
  /* Listar los Submissions del Contestant especificado */
  def submissions() {
    
    Contestant c = Contestant.get(params.id)
    list = c.submissions
    [contestant:c, list:list]
  }
}