<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page import="com.minos.onlinejudge.domain.Contest" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Active Contests</title>
  </head>
  <body>
    <div id="list-contest" class="content scaffold-list" role="main">
      <h1>Active Contest</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Start</th>
            <th>Duration</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <g:each in="${contestList}" status="i" var="actualContest">
            <g:if test="${actualContest.get(0).status != Contest.ST_FINISHED}">
              <tr>
                <td>${fieldValue(bean: actualContest.get(0), field: "title")}</td>
                <td>${actualContest.get(0).startTime.format("dd/MM/yyyy")}</td>
                <td>${actualContest.get(2).format("HH:mm")}</td>
                <g:if test="${actualContest.get(0).status == Contest.ST_CREATED}">
                  <td>Start in: ${actualContest.get(3).format("HH:mm:ss")}</td>
                </g:if>
                <g:else>
                  <td>Remaining time: ${actualContest.get(3).format("HH:mm:ss")}</td>
                </g:else>
                <g:if test="${actualContest.get(1) == false}">
                  <td><g:link action="register" params="[contestID:actualContest.get(0).id]">Register</g:link></td>
                </g:if>
                <g:elseif test="${actualContest.get(0).status == Contest.ST_CREATED}">
                  <td><g:link action="unregister" params="[contestID:actualContest.get(0).id]">Unregister</g:link></td>
                </g:elseif>
                <g:else>
                  <td><g:link action="problems" params="[contestID:actualContest.get(0).id]">Enter</g:link></td>
                </g:else>
              </tr>
            </g:if>
          </g:each>
        </tbody>
      </table>

      <h1>Past Contest</h1>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Start</th>
            <th>Duration</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <g:each in="${contestList}" status="i" var="actualContest">
            <g:if test="${actualContest.get(0).status == Contest.ST_FINISHED}">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${fieldValue(bean: actualContest.get(0), field: "title")}</td>
                <td>${actualContest.get(0).startTime.format("dd/MM/yyyy")}</td>
                <td>${actualContest.get(2).format("HH:mm")}</td>
                <td></td>
                <td><g:link action="problems" params="[contestID:actualContest.get(0).id]">Enter</g:link></td>
              </tr>
            </g:if>
          </g:each>
        </tbody>
      </table>
    </div>
  </body>
</html>
