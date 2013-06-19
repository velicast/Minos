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
      resultList.add([currContest, registered])
    }
    
    resultList.sort { it[0].startTime }
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
    
    [problemList: contest.problems.sort(), contest: contest]
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
    
    [problems: contest.problems.sort(), contest: contest]
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
    def submissions = contestant.submissions.sort().reverse()
    
    [submissions: submissions, contest: contest]
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
    
    Standings standings = new Standings(contest)
    int cols = standings.problemIdentifiers.length
    int rows = standings.positions.length
    
      // codigo html de la tabla generada
    def table = "<table border=1>\n"
    table += "<tr>\n"
    table += "<th>#</th>\n"
    table += "<th>Contestant</th>\n"
    table += "<th>Solved</th>\n"
    table += "<th>Penalty</th>\n"
    for (int i = 0; i < cols; ++i) {
      table += "<th>" + standings.problemIdentifiers[i] + "</th>\n"
    }
    table += "</tr>\n"
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
    table += "</table>"

    [htmlTable: table, contest: contest]
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
    
    [problems: contest.problems.sort(), clarifications: clarifications, contest: contest]
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
  
  def unregister(contest, user) {
    
    Contestant contestant = Contestant.findByUser(user)
    contestant.delete(true)
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
      // No se encuentra registrado
    if (!contestService.isRegistered(contest, session.user)) {
      redirect(controller: "contest")
      return false
    }
    return true
  }
}
