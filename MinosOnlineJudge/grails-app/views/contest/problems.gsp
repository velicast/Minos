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
      <h1>Problem List</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>

      <table>
        <thead>
          <tr>
            <g:sortableColumn property="title" title="${message(code: 'problem.id.label', default: 'ID')}" />
            <g:sortableColumn property="title" title="${message(code: 'problem.title.label', default: 'Name')}" />
            <g:sortableColumn property="title" title="${message(code: 'problem.timeLimit.label', default: 'Time Limit')}" />
            <g:sortableColumn property="title" title="${message(code: 'problem.memoryLimit.label', default: 'Memory Limit')}" />
          </tr>
        </thead>
        <tbody>
          <g:each in="${problemList}" status="i" var="actualProblem">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
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
