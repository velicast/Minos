<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Signup</title>
  </head>
  <body>
    <h1>Signup</h1>
    <g:form name="loginForm" action="signup">
      <a>Username:</a>
      <g:textField name="username"/>
      <br>
      <a>Password:</a>
      <g:passwordField name="password"/>
      <br>
      <a>E-mail:</a>
      <g:textField name="email"/>
      <br>
      <a>Institution:</a>
      <g:textField name="institution"/>
      <br>
      <g:hiddenField name="role" value="user"/>
      <g:submitButton name="submmit" value="Register" />
    </g:form>
  </body>
</html>
