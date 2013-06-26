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
    
    User u = User.findByDbUsername("admin")
    User admin = null
    
    if (!u) {
      admin = new User()
      admin.role = "admin"
      admin.username = "admin"
      admin.dbUsername = "admin"
      admin.password = ("minosonlinejudge").encodeAsPassword()
      admin.email = "admin@minos.com"
      admin.institution = "Universidad Nacional de Colombia"
      admin.save(true)
    } else {
      admin = u
    }
      // -----------------
      // CONTEST DE PRUEBA
      // -----------------
    
    Contest c = Contest.findByTitle("Be a Hacker!")
    Contest contest = null
    
    if (!c) {
      contest = new Contest()
      contest.title = "Be a Hacker!"
      contest.status = Contest.ST_CREATED;
      contest.startTime = new Date(113, 5, 25,  22, 00, 0)
      contest.endTime   = new Date(113, 5, 26, 14, 00, 0)
      contest.save(true)
    } else {
      contest = c
    }
    
    c = Contest.findByTitle("Demostrarion Contest")
    Contest contest1 = null
    
    if (!c) {
      contest1 = new Contest()
      contest1.title = "Demostrarion Contest"
      contest1.status = Contest.ST_CREATED
      contest1.startTime = new Date(113, 5, 26, 14, 00, 0)
      contest1.endTime   = new Date(113, 5, 26, 18, 15, 0)
      contest1.save(true)
    } else {
      contest1 = c
    }
    
    c = Contest.findByTitle("Future Contest")
    if (!c) {
      Contest contest2 = new Contest()
      contest2.title = "Future Contest"
      contest2.status = Contest.ST_CREATED
      contest2.startTime = new Date(113, 5, 26, 18, 15, 0)
      contest2.endTime   = new Date(113, 5, 26, 20, 15, 0)
      contest2.save(true)
    }
    
      // -------------------
      // PROBLEMAS DE PRUEBA
      // -------------------
    Problem p = Problem.findByTitle("Speaking in tonges")
    
    if (!p) {
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
    //p1.save(true)
    
      contest.addToProblems(p1)
    }
    
    p = Problem.findByTitle("A+B Problem")
    
    if (!p) {
    
      Problem p2 = new Problem()
      p2.author = "Anonimo"
      p2.typeChecker = 0
      p2.numberOfTest = 7
      p2.memoryLimit = 32768
      p2.stackLimit = 32768
      p2.outputLimit = 4096
      p2.timeLimit = 1000
      p2.uploader = admin
      p2.contest = contest1
      p2.statement = "Sum two interger values and print the result"
      p2.title = "A+B Problem"
      p2.alphabet = 'A'
      p2.tokenSeparators = "\n\r"
      p2.rootPath = ""
      p2.save(true)
      p2.rootPath = Minos.PROBLEM_DIR + "/problem" + p2.id
      //p2.save(true)
      
      contest1.addToProblems(p2)
    }
    
    p = Problem.findByTitle("Easter Eggs")
    
    if (!p) {
    
      Problem p3 = new Problem()
      p3.author = "Codeforces"
      p3.typeChecker = 1
      p3.numberOfTest = 32
      p3.memoryLimit = 32768
      p3.stackLimit = 32768
      p3.outputLimit = 4096
      p3.timeLimit = 2000
      p3.uploader = admin
      p3.contest = contest
      p3.statement = "See in codeforces..."
      p3.title = "Easter Eggs"
      p3.alphabet = 'B'
      p3.tokenSeparators = "\n\r\t "
      p3.rootPath = ""
      p3.save(true)
      p3.rootPath = Minos.PROBLEM_DIR + "/problem" + p3.id
      //p3.save(true)
      
      contest.addToProblems(p3)
    }
    
    p = Problem.findByTitle("Cut Ribbon")
    
    if (!p) {
    
      Problem p4 = new Problem()
      p4.author = "Codeforces"
      p4.typeChecker = 0
      p4.numberOfTest = 58
      p4.memoryLimit = 65536
      p4.stackLimit = 65536
      p4.outputLimit = 4096
      p4.timeLimit = 2000
      p4.uploader = admin
      p4.contest = contest
      p4.statement = "See in codeforces..."
      p4.title = "Cut Ribbon"
      p4.alphabet = 'C'
      p4.tokenSeparators = "\n\r\t "
      p4.rootPath = ""
      p4.save(true)
      p4.rootPath = Minos.PROBLEM_DIR + "/problem" + p4.id
      //p4.save(true)
      
      contest.addToProblems(p4)
    }
    
    p = Problem.findByTitle("Chat Room")
    
    if (!p) {
    
      Problem p5 = new Problem()
      p5.author = "Codeforces"
      p5.typeChecker = 0
      p5.numberOfTest = 40
      p5.memoryLimit = 32768
      p5.stackLimit = 32768
      p5.outputLimit = 4096
      p5.timeLimit = 2000
      p5.uploader = admin
      p5.contest = contest1
      p5.statement = "See in codeforces..."
      p5.title = "Chat Room"
      p5.alphabet = 'B'
      p5.tokenSeparators = "\n\r\t "
      p5.rootPath = ""
      p5.save(true)
      p5.rootPath = Minos.PROBLEM_DIR + "/problem" + p5.id
      //p4.save(true)
      
      contest1.addToProblems(p5) 
    }
    
    p = Problem.findByTitle("Word")
    
    if (!p) {
    
      Problem p6 = new Problem()
      p6.author = "Codeforces"
      p6.typeChecker = 0
      p6.numberOfTest = 30
      p6.memoryLimit = 32768
      p6.stackLimit = 32768
      p6.outputLimit = 4096
      p6.timeLimit = 2000
      p6.uploader = admin
      p6.contest = contest1
      p6.statement = "See in codeforces..."
      p6.title = "Word"
      p6.alphabet = 'C'
      p6.tokenSeparators = "\n\r\t "
      p6.rootPath = ""
      p6.save(true)
      p6.rootPath = Minos.PROBLEM_DIR + "/problem" + p6.id
      //p4.save(true)
      
      contest1.addToProblems(p6) 
    }
    
    p = Problem.findByTitle("System of Equations")
    
    if (!p) {
    
      Problem p7 = new Problem()
      p7.author = "Codeforces"
      p7.typeChecker = 0
      p7.numberOfTest = 54
      p7.memoryLimit = 32768
      p7.stackLimit = 32768
      p7.outputLimit = 4096
      p7.timeLimit = 2000
      p7.uploader = admin
      p7.contest = contest
      p7.statement = "See in codeforces..."
      p7.title = "System of Equations"
      p7.alphabet = 'D'
      p7.tokenSeparators = "\n\r\t "
      p7.rootPath = ""
      p7.save(true)
      p7.rootPath = Minos.PROBLEM_DIR + "/problem" + p7.id
      //p4.save(true)
      
      contest.addToProblems(p7) 
    }
    
    p = Problem.findByTitle("Minimum Scalar Product")
    
    if (!p) {
    
      Problem p8 = new Problem()
      p8.author = "Codeforces"
      p8.typeChecker = 0
      p8.numberOfTest = 1
      p8.memoryLimit = 65536
      p8.stackLimit = 65536
      p8.outputLimit = 4096
      p8.timeLimit = 4000
      p8.uploader = admin
      p8.contest = contest
      p8.statement = "See in codeforces..."
      p8.title = "Minimum Scalar Product"
      p8.alphabet = 'E'
      p8.tokenSeparators = "\0"
      p8.rootPath = ""
      p8.save(true)
      p8.rootPath = Minos.PROBLEM_DIR + "/problem" + p8.id
      //p4.save(true)
      
      contest.addToProblems(p8) 
    }
    
    contest.save(true)
    contest1.save(true)
    
    def contests = Contest.list()
    for (Contest con : contests) {
      ContestTimeTracer.trace(con)
    }
    
    def submissions = Submission.list(sort: "date", order: "desc")
    for (Submission s : submissions) {
      if (s.status == Minos.IN_QUEUE) {
        Minos.judge(s)
      }
    }
  }
  
  def destroy = {
  }
}
