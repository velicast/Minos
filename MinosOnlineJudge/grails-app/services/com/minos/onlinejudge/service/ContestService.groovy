package com.minos.onlinejudge.service

import com.minos.onlinejudge.domain.Contestant
import com.minos.onlinejudge.domain.Contest

class ContestService {

  def isRegistered(contest, user) {
    return Contestant.findByUserAndContest(user, contest) != null
  }
  
  /**
   * Retorna la diferencia en HH-MM-SS entre las fechas src y dst
   */
  def getDiffTime(src, dst) {
    
    Calendar cSrc = Calendar.getInstance()
    cSrc.setTime(src)
    Calendar cDst = Calendar.getInstance()
    cDst.setTime(dst)
    
      // Diferencia en segundos
    int diff = Math.abs(cSrc.getTimeInMillis() - cDst.getTimeInMillis()) / 1000
    
    int hours = diff / (60 * 60);
    diff %= (60 * 60);
    int minutes = diff / 60;
    int seconds = diff % 60;
    
    Date dt = new Date(0, 0, 0, hours, minutes, seconds)
    return dt
  }
  
  void stateChanged(src) {
    
    Contest s = Contest.lock(src.id)
    s.status = src.status
    s.save(flush:true)
  }
}
