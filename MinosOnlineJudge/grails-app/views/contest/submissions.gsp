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
    <title>${contest.title} Submission's</title>
  </head>
  <body>
    <g:render template="/layouts/contest" />
    
    <div id="mysubmissions" class="content scaffold-list" role="main">
      <h1>My Submissions</h1>
      <table>
        <thead>
          <tr>
            <th>#</th>
            <th>When</th>
            <th>Contestant</th>
            <th>Problem</th>
            <th>Lang</th>
            <th>Veredict</th>
            <th>Time</th>
            <th>Memory</th>
          </tr>
        </thead>
        <tbody>
          <g:each in="${submissions}" var="s">
            <tr>
            <td>${s.id}</td>
            <td>${s.date.format("dd/MM/yyyy HH:mm:ss")}</td>
            <td>${s.contestant.user.username}</td>
            <td>${s.problem.title}</td>
            <td>${Minos.LANG_NAME[s.language]}</td>
            <td>${Minos.STATUS_NAME[s.status]}</td>
            <td>${s.timeElapsed + " ms"}</td>
            <td>${s.memoryUsed + " KB"}</td>
            </tr>
          </g:each>
        </tbody>
      </table>
    </div>
  </body>
</html>
