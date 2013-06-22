package com.minos.onlinejudge.controller

import com.minos.onlinejudge.domain.User
import com.minos.onlinejudge.groovy.Standings

class UserController {

  /**
   * Pagina principal del usuario
   * 
   * @param username Nombre del usuario a mostrar
   * 
   * @return user Usuario a desplegar
   */
  def index() {
    
    [user: User.findByUsername(params.username)]
  }
  
  /**
   * Despliega la configuracion de perfil del usuario en sesio'n
   * 
   * @return user Usuario en sesio'n
   */
  def settings() {
    
    if (!session.user) {
      redirect(action: "login")
      return
    }
    
    [user: session.user]
  }
  
  /* Crea una nueva cuenta de usuario */
  def signup() {
    
    if (request.method == 'POST') {
      params.dbUsername = params.username.toLowerCase()
      params.password = params.password.encodeAsPassword()
      
      def u = new User(params)
      
      if (!u.save()) {
        return [newUser:u]
      } else {
        session.user = u
        redirect(uri: "")
      }
    } else if (session.user) {
      redirect(uri: "")
    }
  }
  
  /* Inicia sesio'n con cuenta de usuario existente */
  def login() {
    
    if (request.method == 'POST') {
      def password = params.password.encodeAsPassword()
      def dbUsername = params.username.toLowerCase()
      
      def u = User.findByDbUsernameAndPassword(dbUsername, password)
      if (u) {
          session.user = u
          redirect(uri: "")
      } else {
        flash.message = "User not found"
      }
    } else if (session.user) {
        redirect(uri: "")
    }
  }
  
  /* Cierra sesio'n actual */
  def logout() {
    
    session.invalidate()
    redirect(uri: "")
  }
  
  /**
   * Metodo llamado cuando es actualizada la informacion del usuario en sesio'n
   * 
   * @param todos los parametros de User
   */
  def updateSettings() {
    
    /* actualizar datos del usuario en sesio'n aqui' */
  }
  
  def startpage() {
      redirect(uri: "")
  }
}
