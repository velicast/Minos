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
      <p>Problem: ${problem.alphabet}. ${problem.title}</p>
      <g:uploadForm controller="submission" action="submit" name="submissionForm" onsubmit="return checkTargetFile()">
        <a>Language:</a>
        <select name="language">
          <option value=${Minos.CPP_LANG}>${Minos.LANG_NAME[Minos.CPP_LANG]}</option>
          <option value=${Minos.JAVA_LANG}>${Minos.LANG_NAME[Minos.JAVA_LANG]}</option>
        </select>
        <br>
        <a>Source:</a>
        <input type="file" name="source" size="10"/>
        <br>
        <g:hiddenField name="submitDate"/>
        <g:hiddenField name="problemID" value="${problem.id}"/>
        <g:submitButton name="submit" value="Submit" />
      </g:uploadForm>
    </div>
  </body>
</html>
