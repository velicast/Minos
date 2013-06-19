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
            <g:sortableColumn property="title" title="${message(code: 'contest.title.label', default: 'Name')}" />
            <g:sortableColumn property="startTime" title="${message(code: 'contest.startTime.label', default: 'Start')}" />
            <g:sortableColumn property="endTime" title="${message(code: 'contest.endTime.label', default: 'End')}" />
            <g:sortableColumn property="title" title="${message(code: 'contest.title.label', default: 'Register')}" />
          </tr>
        </thead>
        <tbody>
          <g:each in="${contestList}" status="i" var="actualContest">
            <g:if test="${actualContest.get(0).status != Contest.ST_FINISHED}">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${fieldValue(bean: actualContest.get(0), field: "title")}</td>
                <td><g:formatDate date="${actualContest.get(0).startTime}" type="datetime" format="yyyy-MM-dd HH:mm"/></td>
                <td><g:formatDate date="${actualContest.get(0).endTime}" type="datetime" format="yyyy-MM-dd HH:mm"/></td>
                <g:if test="${actualContest.get(0).status != Contest.ST_FINISHED}">
                  <g:if test="${actualContest.get(1) == false}">
                    <td><g:link action="register" params="[contestID:actualContest.get(0).id]">Register</g:link></td>
                  </g:if>
                  <g:elseif test="${actualContest.get(0).status == Contest.ST_CREATED}">
                    <td>Registered</td>
                  </g:elseif>
                  <g:else>
                    <td><g:link action="problems" params="[contestID:actualContest.get(0).id]">Enter</g:link></td>
                  </g:else>
                </g:if>
              </tr>
            </g:if>
          </g:each>
        </tbody>
      </table>

      <div class="pagination">
        <g:paginate total="${contestTotal}" />
      </div>

      <h1>Past Contest</h1>
      <table>
        <thead>
          <tr>
            <g:sortableColumn property="title" title="${message(code: 'contest.title.label', default: 'Name')}" />
            <g:sortableColumn property="startTime" title="${message(code: 'contest.startTime.label', default: 'Start')}" />
            <g:sortableColumn property="endTime" title="${message(code: 'contest.endTime.label', default: 'End')}" />
          </tr>
        </thead>
        <tbody>
          <g:each in="${contestList}" status="i" var="actualContest">
            <g:if test="${actualContest.get(0).status == Contest.ST_FINISHED}">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${fieldValue(bean: actualContest.get(0), field: "title")}</td>
                <td><g:formatDate date="${actualContest.get(0).startTime}" type="datetime" format="yyyy-MM-dd HH:mm"/></td>
                <td><g:formatDate date="${actualContest.get(0).endTime}" type="datetime" format="yyyy-MM-dd HH:mm"/></td>
              </tr>
            </g:if>
          </g:each>
        </tbody>
      </table>
    </div>
  </body>
</html>
