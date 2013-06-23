package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.Clarification
import com.minos.onlinejudge.domain.Contest

class ClarificationController {

  /**
   * Realiza el envi'o de una clarificacion. El usuario actual
   * es el que la realiza
   * 
   * @param contestID Identificador del contest en el que se realiza
   *        la clarificacio'n
   * @param submitDate hora a la que se realiza la clarificacio'n
   * @param problemName Nombre del problema al que se realiza la clarificacio'n o "General"
   */
  def submit() {
    
    Contest contest = Contest.get(params.contestID)
    
    Clarification clarification = new Clarification()
    clarification.question = params.question
    clarification.answer = ""
    clarification.problem = params.problemName
    clarification.date = new Date(params.submitDate)
    clarification.contest = contest
    clarification.user = session.user
    clarification.save(true)
    
    redirect(controller: "contest", action:"clarifications", params: [contestID : contest.id])
  }
}
