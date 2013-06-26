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
    <g:javascript src="clarificationChecker.js" />
    <title>Clarifications - Minos</title>
  </head>
  <body>
    <g:render template="/layouts/contest" />
    
    <div id="history" class="content scaffold-list" role="main">
      <h1>Clarifications</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Author</th>
            <th>Problem</th>
            <th>When</th>
            <th>Question</th>
            <th>Answer</th>
          </tr>
        </thead>
        <tbody>
          <g:each in="${clarificationList}" status="i" var="actualClarification">
            <tr>
              <td>${actualClarification.id}</td>
              <td>${actualClarification.user.username}</td>
              <td>${actualClarification.problem}</td>
              <td>${actualClarification.date.format("dd/MM/yyyy HH:mm:ss")}</td>
              <td><textarea readonly>${actualClarification.question}</textarea></td>
              <td><textarea readonly>${actualClarification.answer}</textarea></td>
            </tr>
          </g:each>
        </tbody>
      </table>
    </div>
    
    <div align="center">
      <div id="makeclarification" class="submit">
        <g:if test="${contest.status != Contest.ST_FINISHED}">
          <h1>Make a clarification</h1>
          <g:uploadForm controller="clarification" action="submit"  name="clarificationForm" onsubmit="return checkQuestion()">
            <table>
              <tr>
                <td><label for="comment">Problem:</label></td>
                <td>
                  <select name="problemName">
                    <option value="General">General</option>
                    <g:each in="${problemList}" status="i" var="actualProblem">
                      <option value="${actualProblem.alphabet}. ${actualProblem.title}">${actualProblem.alphabet}. ${actualProblem.title}</option>
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
            <g:hiddenField name="submitDate"/>
            <g:hiddenField name="contestID" value="${contest.id}"/>
          </g:uploadForm>
        </g:if>
        <g:else>
          <label for="comment">The contest has finished</label>
        </g:else>
      </div>
    </div>
  </body>
</html>
