<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Problems - Minos</title>
  </head>
  <body>
    <g:render template="/layouts/contest" />
    
    <div id="list-problems" class="content scaffold-list" role="main">
      <h1>Problems</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Time Limit</th>
            <th>Memory Limit</th>
          </tr>
        </thead>
        <tbody>
          <g:each in="${problemList}" status="i" var="actualProblem">
            <tr>
              <td>${actualProblem.id}</td>
              <td><g:link controller="problem" action="show" params="[problemID:actualProblem.id]">${actualProblem.title}</g:link></td>
              <td>${actualProblem.timeLimit / 1000} seconds</td>
              <td>${actualProblem.memoryLimit / 1024} MB</td>
            </tr>
          </g:each>
        </tbody>
      </table>
    </div>
  </body>
</html>
