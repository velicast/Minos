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
    <title>Active Contests</title>
    <g:javascript src="submitChecker.js" />
  </head>
  <body>
    <h1>List goes here</h1>
    <a id="uploadStatus">Make a submission</a>
  <g:uploadForm controller="submission" action="submit" name="submissionForm" onsubmit="return checkTargetFile()">
    <a>Language:</a>
    <select name="language">
      <option value=${Minos.CPP_LANG}>GNU C++ 4.7</option>
      <option value=${Minos.JAVA_LANG}>Java 7</option>
    </select>
    <br>
    <a>Source:</a>
    <input type="file" name="source" size="10"/>
    <br>
    <g:hiddenField name="submitDate"/>
    <g:submitButton name="submit" value="Submit" />
  </g:uploadForm>
  </body>
</html>
