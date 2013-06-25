package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.Contest
import com.minos.onlinejudge.domain.Contestant
import com.minos.onlinejudge.domain.Problem
import com.minos.onlinejudge.domain.Submission
import com.minos.onlinejudge.groovy.Standings
import com.minos.onlinejudge.domain.User
import com.minos.onlinejudge.domain.Clarification

class ContestController {
  
  def contestService
  
  /**
   * Lista los contest activos, en ejecucion y pasados. Cada registro de
   * la lista contiene el contest y un booleano que indica si el usuario
   * en sesio'n se encuentra registrado o no.
   * 
   * @return contestList Lista de contests
   */
  def index() {
    
      // So'lo usuarios logeados
    if (session.user == null) {
      redirect(controller: "user", action: "login")
      return
    }
    
    def contests = Contest.list()
    def resultList = []
    
    for (currContest in contests) {
      def registered = contestService.isRegistered(currContest, session.user)
      
      Calendar now = Calendar.getInstance()
      def countDown = contestService.getDiffTime(now.getTime(), currContest.startTime)
      def duration = contestService.getDiffTime(currContest.startTime, currContest.endTime)
      if (currContest.status == Contest.ST_RUNNING) {
        countDown = contestService.getDiffTime(now.getTime(), currContest.endTime)
      }
      
      resultList.add([currContest, registered, duration, countDown])
    }
    
    resultList.sort { a, b ->
      if (a[0].startTime.after(b[0].startTime)) return -1
      return 1
    }
    [contestList: resultList, contestTotal: Contest.count()]
  }
  
  /**
   * Lista los problemas del contest especificado.
   *
   * @param id Identificador del contest
   * 
   * @return problems Set de problemas
   * @return contest Contest actual
   */
  def problems() {
    
    Contest contest = Contest.get(params.contestID)
    if (!checkAccess(contest)) {
      return
    }
    Calendar now = Calendar.getInstance()
    def rt = contestService.getDiffTime(now.getTime(), contest.endTime)
    
    [problemList: contest.problems.sort(), contest: contest, remainingTime: rt]
  }
  
  /**
   * Despliega el formulario de envi'os para problemas del
   * contest especificado
   *
   * @param id Identificador del contest
   * 
   * @return problems Problemas a los que se le puede realizar envio
   * @return contest Contest actual
   */
  def submit() {
    
    Contest contest = Contest.get(params.contestID)
    if (!checkAccess(contest)) {
      return
    }
    Calendar now = Calendar.getInstance()
    def rt = contestService.getDiffTime(now.getTime(), contest.endTime)
    
    [problemList: contest.problems.sort(), contest: contest, remainingTime: rt]
  }
  
  /**
   * Lista los envi'os realizados del contest especificado para
   * el usuario en sesion
   * 
   * @param id Identificador del contest
   * 
   * @return submissions Envios realizados por el usuario
   * @return contest Contest actual
   */
  def submissions() {
    
    Contest contest = Contest.get(params.contestID)
    if (!checkAccess(contest)) {
      return
    }
    
    Contestant contestant = Contestant.findByUserAndContest(session.user, contest)
    def submissions
    if (contestant) {
      submissions = contestant.submissions.sort().reverse()
    }
    Calendar now = Calendar.getInstance()
    def rt = contestService.getDiffTime(now.getTime(), contest.endTime)
    
    [submissions: submissions, contest: contest, remainingTime: rt]
  }
  
  /**
   * Despliegla la tabla de posiciones del contest especificado
   * 
   * @param id Identificador del contest
   * 
   * @return htmlTable Codigo HTML de la tabla de los Standings
   * @return contest Contest actual
   */
  def standings() {
    
    Contest contest = Contest.get(params.contestID)
    if (!checkAccess(contest)) {
      return
    }
    
      // codigo html de la tabla generada
    def table = "<table>\n<thead>\n"
    table += "<tr>\n"
    table += "<th>#</th>\n"
    table += "<th>Contestant</th>\n"
    table += "<th>Solved</th>\n"
    table += "<th>Penalty</th>\n"
    
    if (contest.contestants.size() == 0) {
      table += "</thead>\n</table>" 
    } else {
        Standings standings = new Standings(contest)
        int cols = standings.problemIdentifiers.length
        int rows = standings.positions.length
    
        for (int i = 0; i < cols; ++i) {
        table += "<th>" + standings.problemIdentifiers[i] + "</th>\n"
        }
        table += "</tr>\n</thead>\n<tbody>"
        for (int i = 0; i < rows; ++i) {
          def r = standings.positions[i]
          table += "<tr>\n"
          table += "<td>" + r.position + "</td>\n"
          table += "<td>" + r.target.user.username + "</td>\n"
          table += "<td>" + r.solvedProblems + "</td>\n"
          table += "<td>" + r.penaltyTime + "</td>\n"
          for (int j = 0; j < cols; ++j) {
            int time = r.solvedTime[j]
            int att = r.attempts[j]
            table += "<td>" + (att == 0 ? "--" : att) + "/" + (time == 0 ? "--" : time) + "</td>\n"
          }
          table += "</tr>\n"
        }
        table += "</tbody>\n</table>"
    }

    Calendar now = Calendar.getInstance()
    def rt = contestService.getDiffTime(now.getTime(), contest.endTime)
    
    [htmlTable: table, contest: contest, remainingTime: rt]
  }
  
  /* Despliega el formulario para hacer clarificaciones por parte del
   * competidor y adema's muestra el historial de todas las
   * clarificaciones hechas en el contest especificado.
   * 
   * @param id Identificador del contest
   * 
   * @return problems Problemas a los que se les puede enviar clarificacion
   * @return clarifications Historial de clarificaciones
   */
  def clarifications() {
    
    Contest contest = Contest.get(params.contestID)
    if (!checkAccess(contest)) {
      return
    }
    
    def clarifications = contest.clarifications.sort()
    Calendar now = Calendar.getInstance()
    def rt = contestService.getDiffTime(now.getTime(), contest.endTime)
    
    [problemList: contest.problems.sort(), clarificationList: clarifications, contest: contest, remainingTime: rt]
  }
  
  /**
   * Despliega el formulario para hacer pruebas de codigos en linea
   * 
   * @param id
   */
  def customtest() {
    
    Contest contest = Contest.get(params.contestID)
    if (!checkAccess(contest)) {
      return
    }
    /* falta impelemtacion ... */
  }
  
  /* ------------------------------------ */
  /* Metodos utilitarios para el registro */
  /* ------------------------------------ */
  
  def register() {
  
      Contestant reg = new Contestant()
      reg.user = session.user
      reg.contest = Contest.get(params.contestID)
      reg.save()
      redirect(action: "index", params: params)
  }
  
  def unregister() {
    Contest contest = Contest.get(params.contestID)
    Contestant contestant = Contestant.findByUserAndContest(session.user, contest)
    
    contestant?.delete()
    
    redirect(action: "index", params: params)
  }
  
  /**
   * Chequear acceso al contest especificado para el usuario
   * en sesio'n
   * 
   * @param id Identificador del contest
   */
  def checkAccess(contest) {
    
    // So'lo usuarios logeados
    if (session.user == null) {
      redirect(controller: "user", action: "login")
      return false
    }
    
    if (contest.status == Contest.ST_CREATED) {
      redirect(controller: "contest")
      return false
    }
      // No se encuentra registrado
    if (contest.status == Contest.ST_RUNNING && !contestService.isRegistered(contest, session.user)) {
      redirect(controller: "contest")
      return false
    }
    return true
  }
}
