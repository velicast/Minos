package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.Contest

class ContestController {
  
  def index() {
    redirect(action: "list")
  }
  
  def list() {
    
    if (!session.user) {
      redirect(controller:"user")
      return
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
