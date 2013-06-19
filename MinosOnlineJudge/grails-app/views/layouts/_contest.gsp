<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<div id="contestbar">
  <ul>
    <li><g:link controller="contest" action="problems" params="[contestID:contest.id]">Problems</g:link></li>
    <li><g:link controller="contest" action="submit" params="[contestID:contest.id]">Submit</g:link></li>
    <li><g:link controller="contest" action="submissions" params="[contestID:contest.id]">My Submissions</g:link></li>
    <li><g:link controller="contest" action="standings" params="[contestID:contest.id]">Standings</g:link></li>
    <li><g:link controller="contest" action="clarifications" params="[contestID:contest.id]">Clarifications</g:link></li>
  </ul>
</div>
