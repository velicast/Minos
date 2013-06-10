package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.Contest

class ContestController {
  
  def index() {
    redirect(action: "list", params: params)
  }
  
  def list() {
    
    if (session.user) {
      params.max = Math.min(max ?: 10, 100)
      [contestList: Contest.list(params), contestTotal: Contest.count()]
    } else {
      redirect(controller:'user')
    }
  }
  
  /* Despliegla la tabla de posiciones del Contest especificado */
  def standings() {
    
    Contest contest = Contest.get(params.id)
      // codigo html de la tabla generada
    table = "TABLE"
    
    // ------------------------
    // calcular standings aqui'
    // ------------------------
    
    [contest:contest, htmlTable:table]
  }
}
