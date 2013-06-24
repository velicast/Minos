<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<div id="contestbar">
    <ul>
      <li><g:link controller="contestAdminister" action="edit" params="[contestID:contest.id]">Edit</g:link></li>
      <li><g:link controller="contestAdminister" action="problems" params="[contestID:contest.id]">Problems</g:link></li>
      <li><g:link controller="problem" action="create" params="[contestID:contest.id]">Create Problem</g:link></li>
    </ul>
</div>

<div align="center" class="subbar">
    <label for="contest">Contest:</label><label for="contestname">${contest.title}</label>
</div>