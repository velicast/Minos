package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.Problem
import com.minos.onlinejudge.domain.Contest
import com.minos.onlinejudge.domain.Contestant

class ProblemController {

  def contestService
  
  /**
   * Lista todo el problem set
   * 
   * @return problems todo el set de problemas de la pa'gina
   */
  def index() {
      // Solo usuarios logeados
    if (session.user == null) {
      redirect(controller: "user", action: "login")
      return
    }
    
    def problems = Problem.list()
    
    problems.sort { a, b ->
      def da = a.contest.startTime
      def db = b.contest.startTime
      
      if (da.after(db)) return -1
      if (da.before(db)) return 1
      if (a.alphabet > b.alphabet) return -1
      return 1
    }
    
    [problems: problems]
  }
  
  /**
   * Despliega el enunciado del problema especificado.
   * 
   * @param id Identificador del problema a mostrar
   * 
   * @return problem Problema a mostrar
   * @return contest Contest al que pertenece el problema
   */
  def show() {
    
      // Solo usuarios logeados
    if (session.user == null) {
      redirect(controller: "user", action: "login")
      return
    }
      // No puede acceder, usuario no registrado
    Problem problem = Problem.get(params.problemID)
    if (!contestService.isRegistered(problem?.contest, session.user)) {
      redirect(controller: "contest")
      return
    }
    
    [problem: problem, contest: problem.contest]
  }
}