<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page import="com.minos.onlinejudge.Minos" %>
<%@ page import="com.minos.onlinejudge.domain.Contest" %>
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
            <tr>
              <td>${actualClarification.id}</td>
              <td>${actualClarification.user.username}</td>
              <td>${actualClarification.problem.title}</td>
              <td><g:formatDate date="${actualClarification.date}" type="datetime" format="yyyy-MM-dd HH:mm"/></td>
              <td>${actualClarification.question}</td>
              <td>${actualClarification.answer}</td>
            </tr>
          </g:each>
        </tbody>
      </table>
    </div>
    
    <div align="center">
      <div id="makeclarification" class="submit">
        <g:if test="${contest.status != Contest.ST_FINISHED}">
          <h1>Make a clarification</h1>
          <g:uploadForm name="submissionForm">
            <table>
              <tr>
                <td><label for="comment">Problem:</label></td>
                <td>
                  <select name="problemID">
                    <option value="0">General</option>
                    <g:each in="${problemList}" status="i" var="actualProblem">
                      <option value=${actualProblem.id}>${actualProblem.alphabet}. ${actualProblem.title}</option>
                    </g:each>
                  </select>
                </td>
              </tr>
              <tr>
                <td><label for="comment">Question:</label></td>
                <td><textarea name="question" placeholder="Write your clarification" style="width:305px;background: #DAF6B2"></textarea></td>
              </tr>
              <tr>
                <td></td>
                <td style="text-align: center"><g:submitButton name="submit" value="Submit" /></td>
              </tr>
            </table>
          </g:uploadForm>
        </g:if>
        <g:else>
          <label for="comment">The contest has finished</label>
        </g:else>
      </div>
    </div>
  </body>
</html>
