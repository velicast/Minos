package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.User

class UserController {

  def index() {
    
    if (session.user) {
      redirect(controller: "contest") // listar los contests
    } else {
      redirect(action: "login")
    }
  }
  
  /* Crea una nueva cuenta de usuario */
  def signup() {
    
    if (request.method == 'POST') {
      params.username = params.username.toLowerCase()
      params.password = params.password.encodeAsPassword()
      
      def u = new User(params)
      if (!u.save()) {
        return [newUser:u]
      } else {
        session.user = u
        redirect(action:'login')
      }
    } else if (session.user) {
      redirect(controller:'user')
    }
  }
  
  /* Inicia sesion con cuenta de usuario existente */
  def login() {
    
    if (request.method == 'POST') {
      def password = params.password.encodeAsPassword()
      
      def u = User.findByUsernameAndPassword(params.username, password)
      if (u) {
          session.user = u
          redirect(controller:'contest')
      } else {
        flash.message = "User not found"
      }
    } else if (session.user) {
        redirect(controller:'contest')
    }
  }
  
  /* Cierra session actual */
  def logout() {
    
    session.invalidate()
    redirect(controller:'user')
  }
}
