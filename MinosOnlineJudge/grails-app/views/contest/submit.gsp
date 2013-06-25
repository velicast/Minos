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
    <g:javascript src="submissionChecker.js" />
    <title>Submit - Minos</title>
  </head>
  <body>
    <g:render template="/layouts/contest" />
    
    <div align="center">
      <div id="submitpanel" class="submit">
        <g:if test="${contest.status != Contest.ST_FINISHED}">
          <h1>Submit</h1>
          <g:uploadForm controller="submission" action="submit" name="submissionForm" onsubmit="return checkTargetFile()">
            <table>
              <tr>
                <td><label for="comment">Problem:</label></td>
                <td>
                  <select name="problemID">
                    <g:each in="${problemList}" status="i" var="actualProblem">
                      <option value=${actualProblem.id}>${actualProblem.alphabet}. ${actualProblem.title}</option>
                    </g:each>
                  </select>
                </td>
              </tr>
              <tr>
                <td><label for="comment">Language:</label></td>
                <td>
                  <select name="language">
                    <option value=${Minos.CPP_LANG}>${Minos.LANG_NAME[Minos.CPP_LANG]}</option>
                    <option value=${Minos.JAVA_LANG}>${Minos.LANG_NAME[Minos.JAVA_LANG]}</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td><label for="comment">Source:</label></td>
                <td><input type="file" name="source" size="10"/></td>
              </tr>
              <tr>
                <td></td>
                <td style="text-align: center"><g:submitButton name="submit" value="Submit" /></td>
              </tr>
            </table>
            <g:hiddenField name="submitDate"/>
          </g:uploadForm>
        </g:if>
        <g:else>
          <label for="comment">The contest has finished</label>
        </g:else>
      </div>
    </div>
  </body>
</html>
