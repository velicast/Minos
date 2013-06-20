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
    <title>Submit - Minos</title>
    <g:javascript src="submitChecker.js" />
  </head>
  <body>
    <g:render template="/layouts/contest" />
    
    <div id="submitpanel" class="submit">
      <g:uploadForm controller="submission" action="submit" name="submissionForm" onsubmit="return checkTargetFile()">
        <table>
          <tr>
            <td><label>Problem:</label></td>
            <td>
              <select name="problemID">
                <g:each in="${problemList}" status="i" var="actualProblem">
                  <option value=${actualProblem.id}>${actualProblem.title}</option>
                </g:each>
              </select>
            </td>
          </tr>
          <tr>
            <td><label>Language:</label></td>
            <td>
              <select name="language">
                <option value=${Minos.CPP_LANG}>${Minos.LANG_NAME[Minos.CPP_LANG]}</option>
                <option value=${Minos.JAVA_LANG}>${Minos.LANG_NAME[Minos.JAVA_LANG]}</option>
              </select>
            </td>
          </tr>
          <tr>
            <td><label>Source:</label></td>
            <td><input type="file" name="source" size="10"/></td>
          </tr>
          <tr>
            <td colspan="2"><g:hiddenField name="submitDate"/></td>
          </tr>
          <tr>
            <td colspan="2"><g:submitButton name="submit" value="Submit" /></td>
          </tr>
        </table>
        
      </g:uploadForm>
    </div>
  </body>
</html>
