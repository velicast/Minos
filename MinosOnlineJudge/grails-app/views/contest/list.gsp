<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

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
            <g:if test="${actualContest.status != 3}">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${fieldValue(bean: actualContest, field: "title")}</td>
                <td><g:formatDate date="${actualContest.startTime}" type="datetime" format="yyyy-MM-dd HH:mm"/></td>
                <td><g:formatDate date="${actualContest.endTime}" type="datetime" format="yyyy-MM-dd HH:mm"/></td>
                <g:if test="${actualContest.status == 0}">
                  <td>Register</td>
                </g:if>
                <g:else>
                  <td>Running</td>
                </g:else>
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
            <g:if test="${actualContest.status == 3}">
              <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${fieldValue(bean: actualContest, field: "title")}</td>
                <td><g:formatDate date="${actualContest.startTime}" type="datetime" format="yyyy-MM-dd HH:mm"/></td>
                <td><g:formatDate date="${actualContest.endTime}" type="datetime" format="yyyy-MM-dd HH:mm"/></td>
              </tr>
            </g:if>
          </g:each>
        </tbody>
      </table>
      
      <g:if test="${session?.user}">
        <h2>${session?.user?.username}</h2>
      </g:if>
    </div>
  </body>
</html>
