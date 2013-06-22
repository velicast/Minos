package com.minos.onlinejudge.service

import com.minos.onlinejudge.domain.Submission

class SubmissionService {
  
  void stateChanged(Submission src) {
    
    Submission s = Submission.lock(src.id)
    s.status = src.status
    s.timeElapsed = src.timeElapsed
    s.memoryUsed = src.memoryUsed
    s.save(flush:true)
  }
}
