<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create problem</title>
  </head>
  <body>
    <g:render template="/layouts/editcontest" />
    
    <div id="problemcreate">
      <h1>Create contest</h1>
      <g:uploadForm controller="problem" action="docreate" name="submissionForm" params="[contestID:contest.id]">
        <table>
          <tr>
            <td><label for="comment">Title:</label></td>
            <td><input type="text" name="title" /></td>
          </tr>
          <tr>
            <td></td>
            <td></td>
          </tr>
        </table>
      </g:uploadForm>
    </div>
  </body>
</html>
