import com.minos.onlinejudge.Minos
import com.minos.onlinejudge.domain.User
import com.minos.onlinejudge.domain.Contest
import com.minos.onlinejudge.domain.Problem
import com.minos.onlinejudge.domain.Contestant
import com.minos.onlinejudge.domain.Submission

class BootStrap {

  def submissionService
  
  def init = { servletContext ->
    
    Minos.setNotificationTarget(submissionService)
      // ------------------
      // USUARIOS DE PRUEBA
      // ------------------
    def eduarc = new User()
    eduarc.username = "eduarc"
    eduarc.email = "eduarcastrillo@gmail.com"
    eduarc.role = "user"
    eduarc.institution = "Universidad Nacional de Colombia"
    eduarc.password = ("1234").encodeAsPassword()
    eduarc.save(true)
    
    def camilourd = new User()
    camilourd.username = "camilourd"
    camilourd.email = "camilourd@gmail.com"
    camilourd.role = "user"
    camilourd.institution = "Universidad Nacional de Colombia"
    camilourd.password = ("1234").encodeAsPassword()
    camilourd.save(true)
    
      // -----------------
      // CONTEST DE PRUEBA
      // -----------------
    Contest contest = new Contest()
    contest.title = "Test Contest #1"
    contest.status = 0
    contest.startTime = new Date()
    contest.endTime = new Date()
    contest.save(true)
    
    Contest contest1 = new Contest()
    contest1.title = "Test Contest #2"
    contest1.status = 1
    contest1.startTime = new Date()
    contest1.endTime = new Date()
    contest1.save(true)
    
    Contest contest2 = new Contest()
    contest2.title = "Test Contest #3"
    contest2.status = 3
    contest2.startTime = new Date()
    contest2.endTime = new Date()
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
    p1.uploader = null
    p1.contest = contest
    p1.statement = "Speaking in tonges"
    p1.title = "Speaking in tonges"
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
    p2.uploader = null
    p2.contest = contest
    p2.statement = "Sum two interger values and print the result"
    p2.title = "A+B Problem"
    p2.tokenSeparators = "\0"
    p2.rootPath = ""
    p2.save(true)
    p2.rootPath = Minos.PROBLEM_DIR + "/problem" + p2.id
    p2.save(true)
    
    Problem p3 = new Problem()
    p3.author = "Codeforces"
    p3.typeChecker = 1
    p3.numberOfTest = 32
    p3.memoryLimit = 32768
    p3.stackLimit = 32768
    p3.outputLimit = 4096
    p3.timeLimit = 4000
    p3.uploader = null
    p3.contest = contest
    p3.statement = "See in codeforces..."
    p3.title = "Easter Eggs"
    p3.tokenSeparators = "\n\r "
    p3.rootPath = ""
    p3.save(true)
    p3.rootPath = Minos.PROBLEM_DIR + "/problem" + p3.id
    p3.save(true)
    /*
      // ----------------------
      // CONCURSANTES DE PRUEBA
      // ----------------------
    Contestant c1 = new Contestant()
    c1.user = eduarc
    c1.penaltyTime = 0
    c1.solvedProblems = 0
    c1.contest = contest
    c1.save(true)
    
    Contestant c2 = new Contestant()
    c2.user = camilourd
    c2.contest = contest
    c2.penaltyTime = 0
    c2.solvedProblems = 0
    c2.save(true)
    
      // --------------------
      // ENVIOS DE PRUEBA C++
      // --------------------
    /*Submission ac = new Submission()
    ac.contestant = c1
    ac.date = new Date()
    ac.fileName = "ac.cpp"
    ac.language = Minos.CPP_LANG
    ac.memoryUsed = 0
    ac.timeElapsed = 0
    ac.problem = p2
    ac.status = Minos.SUCCESS
    ac.rootPath = ""
    ac.save(true)
    ac.rootPath = Minos.SUBMISSION_DIR + "/sub" + ac.id
    ac.save(true)
    
    Submission wa = new Submission()
    wa.contestant = c1
    wa.date = new Date()
    wa.fileName = "wa.cpp"
    wa.language = Minos.CPP_LANG
    wa.memoryUsed = 0
    wa.timeElapsed = 0
    wa.problem = p2
    wa.status = Minos.SUCCESS
    wa.rootPath = ""
    wa.save(true)
    wa.rootPath = Minos.SUBMISSION_DIR + "/sub" + wa.id
    wa.save(true)
    
    Submission ce = new Submission()
    ce.contestant = c1
    ce.date = new Date()
    ce.fileName = "ce.cpp"
    ce.language = Minos.CPP_LANG
    ce.memoryUsed = 0
    ce.timeElapsed = 0
    ce.problem = p2
    ce.status = Minos.SUCCESS
    ce.rootPath = ""
    ce.save(true)
    ce.rootPath = Minos.SUBMISSION_DIR + "/sub" + ce.id
    ce.save(true)
    
    Submission ii = new Submission()
    ii.contestant = c1
    ii.date = new Date()
    ii.fileName = "ii.cpp"
    ii.language = Minos.CPP_LANG
    ii.memoryUsed = 0
    ii.timeElapsed = 0
    ii.problem = p2
    ii.status = Minos.SUCCESS
    ii.rootPath = ""
    ii.save(true)
    ii.rootPath = Minos.SUBMISSION_DIR + "/sub" + ii.id
    ii.save(true)
    
    Submission mle = new Submission()
    mle.contestant = c1
    mle.date = new Date()
    mle.fileName = "mle.cpp"
    mle.language = Minos.CPP_LANG
    mle.memoryUsed = 0
    mle.timeElapsed = 0
    mle.problem = p2
    mle.status = Minos.SUCCESS
    mle.rootPath = ""
    mle.save(true)
    mle.rootPath = Minos.SUBMISSION_DIR + "/sub" + mle.id
    mle.save(true)
    
    Submission ole = new Submission()
    ole.contestant = c1
    ole.date = new Date()
    ole.fileName = "ole.cpp"
    ole.language = Minos.CPP_LANG
    ole.memoryUsed = 0
    ole.timeElapsed = 0
    ole.problem = p2
    ole.status = Minos.SUCCESS
    ole.rootPath = ""
    ole.save(true)
    ole.rootPath = Minos.SUBMISSION_DIR + "/sub" + ole.id
    ole.save(true)
    
    Submission rte = new Submission()
    rte.contestant = c1
    rte.date = new Date()
    rte.fileName = "rte.cpp"
    rte.language = Minos.CPP_LANG
    rte.memoryUsed = 0
    rte.timeElapsed = 0
    rte.problem = p2
    rte.status = Minos.SUCCESS
    rte.rootPath = ""
    rte.save(true)
    rte.rootPath = Minos.SUBMISSION_DIR + "/sub" + rte.id
    rte.save(true)
    
    Submission tle = new Submission()
    tle.contestant = c1
    tle.date = new Date()
    tle.fileName = "tle.cpp"
    tle.language = Minos.CPP_LANG
    tle.memoryUsed = 0
    tle.timeElapsed = 0
    tle.problem = p2
    tle.status = Minos.SUCCESS
    tle.rootPath = ""
    tle.save(true)
    tle.rootPath = Minos.SUBMISSION_DIR + "/sub" + tle.id
    tle.save(true)
    
      // ---------------------
      // ENVIOS DE PRUEBA JAVA
      // ---------------------
    Submission jac = new Submission()
    jac.contestant = c2
    jac.date = new Date()
    jac.fileName = "AC.java"
    jac.language = Minos.JAVA_LANG
    jac.memoryUsed = 0
    jac.timeElapsed = 0
    jac.problem = p2
    jac.status = Minos.SUCCESS
    jac.rootPath = ""
    jac.save(true)
    jac.rootPath = Minos.SUBMISSION_DIR + "/sub" + jac.id
    jac.save(true)
    
    Submission jce = new Submission()
    jce.contestant = c2
    jce.date = new Date()
    jce.fileName = "CE.java"
    jce.language = Minos.JAVA_LANG
    jce.memoryUsed = 0
    jce.timeElapsed = 0
    jce.problem = p2
    jce.status = Minos.SUCCESS
    jce.rootPath = ""
    jce.save(true)
    jce.rootPath = Minos.SUBMISSION_DIR + "/sub" + jce.id
    jce.save(true)
    
    Submission jii = new Submission()
    jii.contestant = c2
    jii.date = new Date()
    jii.fileName = "II.java"
    jii.language = Minos.JAVA_LANG
    jii.memoryUsed = 0
    jii.timeElapsed = 0
    jii.problem = p2
    jii.status = Minos.SUCCESS
    jii.rootPath = ""
    jii.save(true)
    jii.rootPath = Minos.SUBMISSION_DIR + "/sub" + jii.id
    jii.save(true)
    
    Submission jmle = new Submission()
    jmle.contestant = c2
    jmle.date = new Date()
    jmle.fileName = "MLE.java"
    jmle.language = Minos.JAVA_LANG
    jmle.memoryUsed = 0
    jmle.timeElapsed = 0
    jmle.problem = p2
    jmle.status = Minos.SUCCESS
    jmle.rootPath = ""
    jmle.save(true)
    jmle.rootPath = Minos.SUBMISSION_DIR + "/sub" + jmle.id
    jmle.save(true)
    
    Submission jac1 = new Submission()
    jac1.contestant = c2
    jac1.date = new Date()
    jac1.fileName = "MultipleClasses.java"
    jac1.language = Minos.JAVA_LANG
    jac1.memoryUsed = 0
    jac1.timeElapsed = 0
    jac1.problem = p2
    jac1.status = Minos.SUCCESS
    jac1.rootPath = ""
    jac1.save(true)
    jac1.rootPath = Minos.SUBMISSION_DIR + "/sub" + jac1.id
    jac1.save(true)
    
    Submission jole = new Submission()
    jole.contestant = c2
    jole.date = new Date()
    jole.fileName = "OLE.java"
    jole.language = Minos.JAVA_LANG
    jole.memoryUsed = 0
    jole.timeElapsed = 0
    jole.problem = p2
    jole.status = Minos.SUCCESS
    jole.rootPath = ""
    jole.save(true)
    jole.rootPath = Minos.SUBMISSION_DIR + "/sub" + jole.id
    jole.save(true)
    
    Submission jrte = new Submission()
    jrte.contestant = c2
    jrte.date = new Date()
    jrte.fileName = "RTE.java"
    jrte.language = Minos.JAVA_LANG
    jrte.memoryUsed = 0
    jrte.timeElapsed = 0
    jrte.problem = p2
    jrte.status = Minos.SUCCESS
    jrte.rootPath = ""
    jrte.save(true)
    jrte.rootPath = Minos.SUBMISSION_DIR + "/sub" + jrte.id
    jrte.save(true)
    
    Submission jtle = new Submission()
    jtle.contestant = c2
    jtle.date = new Date()
    jtle.fileName = "TLE.java"
    jtle.language = Minos.JAVA_LANG
    jtle.memoryUsed = 0
    jtle.timeElapsed = 0
    jtle.problem = p2
    jtle.status = Minos.SUCCESS
    jtle.rootPath = ""
    jtle.save(true)
    jtle.rootPath = Minos.SUBMISSION_DIR + "/sub" + jtle.id
    jtle.save(true)
    
    Submission jwa = new Submission()
    jwa.contestant = c2
    jwa.date = new Date()
    jwa.fileName = "WA.java"
    jwa.language = Minos.JAVA_LANG
    jwa.memoryUsed = 0
    jwa.timeElapsed = 0
    jwa.problem = p2
    jwa.status = Minos.SUCCESS
    jwa.rootPath = ""
    jwa.save(true)
    jwa.rootPath = Minos.SUBMISSION_DIR + "/sub" + jwa.id
    jwa.save(true)
    
      // -------------------------------------
      // ENVIOS DE PRUEBA CON CHECKER ESPECIAL
      // -------------------------------------
    Submission ac1 = new Submission()
    ac1.contestant = c1
    ac1.date = new Date()
    ac1.fileName = "ac.cpp"
    ac1.language = Minos.CPP_LANG
    ac1.memoryUsed = 0
    ac1.timeElapsed = 0
    ac1.problem = p3
    ac1.status = Minos.SUCCESS
    ac1.rootPath = ""
    ac1.save(true)
    ac1.rootPath = Minos.SUBMISSION_DIR + "/sub" + ac1.id
    ac1.save(true)
    
    Submission ac2 = new Submission()
    ac2.contestant = c2
    ac2.date = new Date()
    ac2.fileName = "ac.cpp"
    ac2.language = Minos.CPP_LANG
    ac2.memoryUsed = 0
    ac2.timeElapsed = 0
    ac2.problem = p3
    ac2.status = Minos.SUCCESS
    ac2.rootPath = ""
    ac2.save(true)
    ac2.rootPath = Minos.SUBMISSION_DIR + "/sub" + ac2.id
    ac2.save(true)
    
    Submission wa1 = new Submission()
    wa1.contestant = c1
    wa1.date = new Date()
    wa1.fileName = "wa.cpp"
    wa1.language = Minos.CPP_LANG
    wa1.memoryUsed = 0
    wa1.timeElapsed = 0
    wa1.problem = p3
    wa1.status = Minos.SUCCESS
    wa1.rootPath = ""
    wa1.save(true)
    wa1.rootPath = Minos.SUBMISSION_DIR + "/sub" + wa1.id
    wa1.save(true)
    
    Submission wa2 = new Submission()
    wa2.contestant = c2
    wa2.date = new Date()
    wa2.fileName = "wa.cpp"
    wa2.language = Minos.CPP_LANG
    wa2.memoryUsed = 0
    wa2.timeElapsed = 0
    wa2.problem = p3
    wa2.status = Minos.SUCCESS
    wa2.rootPath = ""
    wa2.save(true)
    wa2.rootPath = Minos.SUBMISSION_DIR + "/sub" + wa2.id
    wa2.save(true)
    
    Submission wa3 = new Submission()
    wa3.contestant = c1
    wa3.date = new Date()
    wa3.fileName = "_78B.java"
    wa3.language = Minos.JAVA_LANG
    wa3.memoryUsed = 0
    wa3.timeElapsed = 0
    wa3.problem = p3
    wa3.status = Minos.SUCCESS
    wa3.rootPath = ""
    wa3.save(true)
    wa3.rootPath = Minos.SUBMISSION_DIR + "/sub" + wa3.id
    wa3.save(true)
    
    Submission ac3 = new Submission()
    ac3.contestant = c2
    ac3.date = new Date()
    ac3.fileName = "Main.java"
    ac3.language = Minos.JAVA_LANG
    ac3.memoryUsed = 0
    ac3.timeElapsed = 0
    ac3.problem = p3
    ac3.status = Minos.SUCCESS
    ac3.rootPath = ""
    ac3.save(true)
    ac3.rootPath = Minos.SUBMISSION_DIR + "/sub" + ac3.id
    ac3.save(true)
    
    Submission wa4 = new Submission()
    wa4.contestant = c1
    wa4.date = new Date()
    wa4.fileName = "CF70B.java"
    wa4.language = Minos.JAVA_LANG
    wa4.memoryUsed = 0
    wa4.timeElapsed = 0
    wa4.problem = p3
    wa4.status = Minos.SUCCESS
    wa4.rootPath = ""
    wa4.save(true)
    wa4.rootPath = Minos.SUBMISSION_DIR + "/sub" + wa4.id
    wa4.save(true)
    
    Minos.judge(ac)
    Minos.judge(wa)
    Minos.judge(ce)
    Minos.judge(ii)
    Minos.judge(mle)
    Minos.judge(ole)
    Minos.judge(rte)
    Minos.judge(tle)
    
    Minos.judge(jac)
    Minos.judge(jwa)
    Minos.judge(jce)
    Minos.judge(jii)
    Minos.judge(jmle)
    Minos.judge(jole)
    Minos.judge(jrte)
    Minos.judge(jtle)
    Minos.judge(jac1)
    
    Minos.judge(ac1)
    Minos.judge(ac2)
    Minos.judge(wa1)
    Minos.judge(wa2)
    Minos.judge(wa3)
    Minos.judge(ac3)
    Minos.judge(wa4)*/
  }
  
  def destroy = {
  }
}
