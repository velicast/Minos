package com.minos.onlinejudge.domain

class User {
  String username
	String password
  String email
  String role
  String institution
  
  static hasMany = [clarifications: Clarification]

  static constraints = {
    username(blank: false, unique: true, matches: /[A-Za-z_0-9]*/)
		password(blank: false)
    email(blank: false, email: true, unique: true)
    role(inList:["admin","user"])
    institution()
  }
}