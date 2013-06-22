package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.Submission
import com.minos.onlinejudge.domain.Contestant
import com.minos.onlinejudge.domain.Contest
import com.minos.onlinejudge.domain.Problem
import com.minos.onlinejudge.Minos

class SubmissionController {

  /**
   * Realiza el envi'o de una solucio'n al Minos. El usuario actual
   * es el que lo realiza
   * 
   * @param id Identificador del problema al que se le realiza
   *        el envi'o
   */
  def submit() {
    /* Chequear permiso de acceso aqui' */
    
    def sourceFile = request.getFile("source")
    def fileName = sourceFile.getOriginalFilename()
    
    Problem problem = Problem.get(params.problemID)
    Contest contest = problem.contest
    Contestant contestant = Contestant.findByUserAndContest(session.user, contest)
    
      // Crear nuevo Submission
    Submission submission = new Submission()
    submission.date = new Date(params.submitDate)
    submission.fileName = fileName
    submission.contestant = contestant
    submission.language = Integer.parseInt(params.language)
    submission.problem = problem
    submission.save(true)
    submission.rootPath = Minos.SUBMISSION_DIR + "/sub" + submission.id
    contestant.save(true)
    
    uploadFile(submission, sourceFile)
    
      // JUZGAR!!!
    Minos.judge(submission)
    
    redirect(controller: "contest", action:"submissions", params: [contestID : contest.id])
  }
  
  /* Tranferir archivo a carpeta de envio */
  def uploadFile(submission, sourceFile) {
    
    def dstPath = new File(submission.rootPath)
    if (!dstPath.exists()) {
      dstPath.mkdir()
    }
    //try {
      sourceFile.transferTo(new File(submission.rootPath + "/" + submission.fileName))
    //} catch (Exception ex) {
        // LOG error here!
     // submission.status = Minos.INTERNAL_ERROR
     // submission.save(true)
   // }
  }
}