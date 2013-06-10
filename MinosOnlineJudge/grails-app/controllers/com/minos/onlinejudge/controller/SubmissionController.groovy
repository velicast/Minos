package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.Submission
import com.minos.onlinejudge.domain.Contestant
import com.minos.onlinejudge.domain.Contest
import com.minos.onlinejudge.domain.Problem
import com.minos.onlinejudge.Minos

class SubmissionController {

  def index() {
    redirect(action: "submit")
  }
  
  def submit() {

    if (!session.user) {
      redirect(controller: "user")
    }
    
    def sourceFile = request.getFile("source")
    def fileName = sourceFile.getOriginalFilename()
    
    params.problemID = 2
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
    submission.save(true)
    
    uploadFile(submission, sourceFile)
    
      // JUZGAR!!!
    Minos.judge(submission)
    
    redirect(controller: "contestant", action:"list", params: [id:contestant.id])
  }
  
  /* Listar todos los envios que han sido realizados */
  def list() {
    [submissions:Submission.list()]
  }
  
  /* Tranferir archivo a carpeta de envio */
  def uploadFile(submission, sourceFile) {
    
    def dstPath = new File(submission.rootPath)
    if (!dstPath.exists()) {
      dstPath.mkdir()
    }
    sourceFile.transferTo(new File(submission.rootPath + "/" + submission.fileName))
  }
}