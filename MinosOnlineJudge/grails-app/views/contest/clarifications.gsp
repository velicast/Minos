<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Clarifications - Minos</title>
  </head>
  <body>
    <g:render template="/layouts/contest" />
    
    <div id="history" class="content scaffold-list" role="main">
      <h1>History</h1>
      <table>
        <thead>
          <tr>
            <g:sortableColumn property="title" title="${message(code: 'clarification.id.label', default: 'ID')}" />
            <g:sortableColumn property="title" title="${message(code: 'clarification.user.username.label', default: 'Author')}" />
            <g:sortableColumn property="title" title="${message(code: 'clarification.problem.label', default: 'Problem')}" />
            <g:sortableColumn property="title" title="${message(code: 'clarification.date.label', default: 'When')}" />
            <g:sortableColumn property="title" title="${message(code: 'clarification.question.label', default: 'Question')}" />
            <g:sortableColumn property="title" title="${message(code: 'clarification.answer.label', default: 'Answer')}" />
          </tr>
        </thead>
        <tbody>
          <g:each in="${clarificationList}" status="i" var="actualClarification">
          </g:each>
        </tbody>
      </table>
    </div>
    
    <div id="makeclarification">
    </div>
  </body>
</html>
