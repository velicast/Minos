package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.Clarification

class ClarificationController {

  def index() {
    
  }
  
  def submit() {
      
      redirect(controller: "contest", action:"clarifications", params: [contestID : contest.id])
  }
}
