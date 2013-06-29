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
    <div align="center">
      <div id="loginpanel" class="submit">
        <h1>Login</h1>
        <g:form name="loginForm" action="login">
          <table>
            <tr>
              <td colspan="2"><p>${flash?.message}</p></td>
            </tr>
            <tr>
              <td><label for="comment">Username:</label></td>
              <td><g:textField name="username"/></td>
            </tr>
            <tr>
              <td><label for="comment">Password:</label></td>
              <td><g:passwordField name="password"/></td>
            </tr>
            <tr>
              <td></td>
              <td><g:submitButton name="submmit" value="Login" /></td>
            </tr>
            <tr>
              <td></td>
              <td><g:link action="signup">Register</g:link></td>
            </tr>
          </table>          
        </g:form>
        
      </div>
    </div>
  </body>
</html>
