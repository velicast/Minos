import com.minos.onlinejudge.ContestTimeTracer
import com.minos.onlinejudge.Minos
import com.minos.onlinejudge.domain.User
import com.minos.onlinejudge.domain.Contest
import com.minos.onlinejudge.domain.Problem
import com.minos.onlinejudge.domain.Contestant
import com.minos.onlinejudge.domain.Submission
import com.minos.onlinejudge.groovy.Standings

class BootStrap {

  def submissionService
  def contestService
  
  def init = { servletContext ->
    
    Minos.setNotificationTarget(submissionService)
    ContestTimeTracer.setNotificationTarget(contestService)
    
    User admin = new User()
    admin.role = "admin"
    admin.username = "admin"
    admin.dbUsername = "admin"
    admin.password = ("admin").encodeAsPassword()
    admin.email = "admin@minos.com"
    admin.institution = "Universidad Nacional de Colombia"
    admin.save(true)
      // -----------------
      // CONTEST DE PRUEBA
      // -----------------
    
    Contest contest = new Contest()
    contest.title = "Test Contest #1"
    contest.status = Contest.ST_RUNNING
    contest.startTime = new Date(113, 5, 22,  7, 30, 0)
    contest.endTime   = new Date(113, 5, 23, 19, 30, 0)
    contest.save(true)
    
    Contest contest1 = new Contest()
    contest1.title = "Test Contest #2"
    contest1.status = Contest.ST_CREATED
    contest1.startTime = new Date(113, 5, 25, 8, 46, 0)
    contest1.endTime   = new Date(113, 5, 25, 9, 05, 0)
    contest1.save(true)
    
    Contest contest2 = new Contest()
    contest2.title = "Test Contest #3"
    contest2.status = Contest.ST_FINISHED
    contest2.startTime = new Date(113, 5, 11, 20, 30, 0)
    contest2.endTime   = new Date(113, 5, 11, 22, 30, 0)
    contest2.save(true)
    
      // -------------------
      // PROBLEMAS DE PRUEBA
      // -------------------
    Problem p1 = new Problem()
    p1.author = "Google Inc."
    p1.typeChecker = 0
    p1.numberOfTest = 1
    p1.memoryLimit = 32768
    p1.stackLimit = 32768
    p1.outputLimit = 4096
    p1.timeLimit = 4000
    p1.uploader = admin
    p1.contest = contest
    p1.statement = "Speaking in tonges"
    p1.title = "Speaking in tonges"
    p1.alphabet = 'A'
    p1.tokenSeparators = "\0"
    p1.rootPath = ""
    p1.save(true)
    p1.rootPath = Minos.PROBLEM_DIR + "/problem" + p1.id
    p1.save(true)
    
    Problem p2 = new Problem()
    p2.author = "Anonimo"
    p2.typeChecker = 0 
    p2.numberOfTest = 2
    p2.memoryLimit = 32768
    p2.stackLimit = 32768
    p2.outputLimit = 4096
    p2.timeLimit = 4000
    p2.uploader = admin
    p2.contest = contest
    p2.statement = "Sum two interger values and print the result"
    p2.title = "A+B Problem"
    p2.alphabet = 'B'
    p2.tokenSeparators = "\0"
    p2.rootPath = ""
    p2.save(true)
    p2.rootPath = Minos.PROBLEM_DIR + "/problem" + p2.id
    p1.save(true)
    
    Problem p3 = new Problem()
    p3.author = "Codeforces"
    p3.typeChecker = 1
    p3.numberOfTest = 32
    p3.memoryLimit = 32768
    p3.stackLimit = 32768
    p3.outputLimit = 4096
    p3.timeLimit = 4000
    p3.uploader = admin
    p3.contest = contest
    p3.statement = "See in codeforces..."
    p3.title = "Easter Eggs"
    p3.alphabet = 'C'
    p3.tokenSeparators = "\n\r "
    p3.rootPath = ""
    p3.save(true)
    p3.rootPath = Minos.PROBLEM_DIR + "/problem" + p3.id
    p1.save(true)
    
    contest.addToProblems(p1)
    contest.addToProblems(p2)
    contest.addToProblems(p3)
    
    contest.save(true)
    
    ContestTimeTracer.trace(contest)
    ContestTimeTracer.trace(contest1)
    ContestTimeTracer.trace(contest2)
    
    java.io.File f = new java.io.File("tmpdir")
    f.mkdir()
  }
  
  def destroy = {
  }
}
