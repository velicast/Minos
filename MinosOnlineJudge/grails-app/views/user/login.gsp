<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
  </head>
  <body>
    <h1>Login</h1>
    <g:form name="loginForm" action="login">
      <p>${flash?.message}</p>
      <a>Username:</a>
      <g:textField name="username"/>
      <br>
      <a>Password:</a>
      <g:passwordField name="password"/>
      <br>
      <g:submitButton name="submmit" value="Login" />
    </g:form>
    <g:link action="signup">Register</g:link>
  </body>
</html>
