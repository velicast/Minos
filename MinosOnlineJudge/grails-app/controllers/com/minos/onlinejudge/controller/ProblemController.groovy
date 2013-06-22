package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.Problem
import com.minos.onlinejudge.domain.Contest
import com.minos.onlinejudge.domain.Contestant
import com.minos.onlinejudge.Minos

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
    Contest contest = problem.contest
    
    if (contest.status == Contest.ST_CREATED) {
      redirect(controller: "contest")
      return
    }
    
    if (contest.status == Contest.ST_RUNNING && !contestService.isRegistered(problem.contest, session.user)) {
      redirect(controller: "contest")
      return
    }
    
    def htmlcode = ""
    def dir = Minos.PROBLEM_DIR + "/problem" + problem.id + "/statement/index.html"
    
    def file= new File(dir)
    htmlcode += "<h1>" + problem.alphabet + ". " + problem.title + "</h1>"
    htmlcode += "<h3>time limit: " + (problem.timeLimit/1000) + " seconds</h3>"
    htmlcode += "<h3>memory limit: " + (problem.memoryLimit/1024) + " megabytes</h3>"
    htmlcode += file.getText()
    
    
    [problem: problem, contest: problem.contest, htmlProblem: htmlcode]
  }
}