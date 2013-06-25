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
    <g:javascript src="jquery.js" />
    <g:javascript src="jquery.simple-dtpicker.js" />
    <g:javascript src="create-contest-calendar.js" />
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.simple-dtpicker.css')}" type="text/css">
    <title>Edit contest</title>
  </head>
  <body>
    <g:render template="/layouts/editcontest" />
    
    <div align="center">
      <div id="submitpanel" class="submit">
        <h1>Edit Contest</h1>
        <g:uploadForm controller="contestAdminister" action="update" name="submissionForm" params="[contestID:contest.id]">
          <table>
            <tr>
              <td><label for="comment">Title:</label></td>
              <td><input type="text" name="title" value="${contest.title}" /></td>
            </tr>
            <tr>
              <td><label for="comment">Start time:</label></td>
              <td><input type="text" name="starttime" value="${contest.startTime.format("yyyy/MM/dd H:m")}" /></td>
            </tr>
            <tr>
              <td><label for="comment">End time:</label></td>
              <td><input type="text" name="endtime" value="${contest.endTime.format("yyyy/MM/dd H:m")}" /></td>
            </tr>
            <tr>
              <td></td>
              <td style="text-align: center"><g:submitButton name="submit" value="Submit" /></td>
            </tr>
          </table>
        </g:uploadForm>
      </div>
    </div>
  </body>
</html>
