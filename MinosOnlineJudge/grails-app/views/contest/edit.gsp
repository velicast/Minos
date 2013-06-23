<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page import="com.minos.onlinejudge.domain.Contest" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit contest</title>
  </head>
  <body>
    <g:render template="/layouts/editcontest" />
    
    <div id="list-contest" class="content scaffold-list" role="main">
      <h1>Active Contest</h1>
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>
      
      <table>
        <thead>
          <tr>
            <g:sortableColumn property="title" title="${message(code: 'contest.id.label', default: 'ID')}" />
            <g:sortableColumn property="title" title="${message(code: 'contest.title.label', default: 'Name')}" />
            <g:sortableColumn property="title" title="${message(code: 'contest.title.label', default: 'Edit')}" />
          </tr>
        </thead>
        <tbody>
          <g:each in="${contestList}" status="i" var="actualContest">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
              <td>${actualContest.id}</td>
              <td>${fieldValue(bean: actualContest, field: "title")}</td>
              <g:if test="${actualContest.status != Contest.ST_FINISHED}">
                <td><g:link action="unregister" params="[contestID:actualContest.id]">Edit</g:link></td>
              </g:if>
              <g:else>
                <td>The contest has finished</td>
              </g:else>
            </tr>
          </g:each>
        </tbody>
      </table>

      <div class="pagination">
        <g:paginate total="${contestTotal}" />
      </div>
    </div>
  </body>
</html>
