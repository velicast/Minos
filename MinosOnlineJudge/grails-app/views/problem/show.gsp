<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page import="com.minos.onlinejudge.Minos" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Problem - ${problem.id} - Minos</title>
    <g:javascript src="submitChecker.js" />
  </head>
  <body>
    <g:render template="/layouts/contest" />
    
    <div id="showproblem">
      <h1>Show description problem here!</h1>
    </div>
    
    <div id="showsubmit">
      <g:uploadForm controller="submission" action="submit" name="submissionForm" onsubmit="return checkTargetFile()">
        <table>
          <tr>
            <td><label for="comment">Problem:</label></td>
            <td><label for="comment">${problem.alphabet}. ${problem.title}</label></td>
          </tr>
          <tr>
            <td><label for="comment">Language:</label></td>
            <td>
              <select name="language">
                <option value=${Minos.CPP_LANG}>${Minos.LANG_NAME[Minos.CPP_LANG]}</option>
                <option value=${Minos.JAVA_LANG}>${Minos.LANG_NAME[Minos.JAVA_LANG]}</option>
              </select>
            </td>
          </tr>
          <tr>
            <td><label for="comment">Source:</label></td>
            <td><input type="file" name="source" size="10"/></td>
          </tr>
          <tr>
            <td><g:hiddenField name="submitDate"/></td>
            <td><g:hiddenField name="problemID" value="${problem.id}"/></td>
          </tr>
          <tr>
            <td></td>
            <td><g:submitButton name="submit" value="Submit" /></td>
          </tr>
        </table>
      </g:uploadForm>
    </div>
  </body>
</html>
