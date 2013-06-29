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
    <div align="center">
      <div id="loginpanel" class="submit">
        <h1>Signup</h1>
        <g:form name="loginForm" action="signup">
          <table>
            <tr>
              <td><label for="comment">Username:</label></td>
              <td><g:textField name="username"/></td>
            </tr>
            <tr>
              <td><label for="comment">Password:</label></td>
              <td><g:passwordField name="password"/></td>
            </tr>
            <tr>
              <td><label for="comment">E-mail:</label></td>
              <td><g:textField name="email"/></td>
            </tr>
            <tr>
              <td><label for="comment">Institution:</label></td>
              <td><g:textField name="institution"/></td>
            </tr>
            <tr>
              <td><g:hiddenField name="role" value="user"/></td>
              <td><g:submitButton name="submmit" value="Register" /></td>
            </tr>
          </table>
        </g:form>
      </div>
    </div>
  </body>
</html>
