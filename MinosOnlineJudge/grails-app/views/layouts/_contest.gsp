<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<g:javascript src="jquery.js" />
<g:javascript src="jquery.jcountdown.js" />
<script type="text/javascript">
  $(document).ready(function() {
    $("#countdown").countdown({
      date: "${remainingTime.format("MM dd, yyyy HH:mm:ss")}",
      hourText: ":",
      minText: ":",
      secText: "",
      dayText: ":",
      leadingZero: true,
      onComplete: function( event ){
        $(this).html("The contest has finished");
      }
    });
  });
</script>

<div id="contestbar">
    <ul>
      <li><g:link controller="contest" action="problems" params="[contestID:contest.id]">Problems</g:link></li>
      <li><g:link controller="contest" action="submit" params="[contestID:contest.id]">Submit</g:link></li>
      <li><g:link controller="contest" action="submissions" params="[contestID:contest.id]">My Submissions</g:link></li>
      <li><g:link controller="contest" action="standings" params="[contestID:contest.id]">Standings</g:link></li>
      <li><g:link controller="contest" action="clarifications" params="[contestID:contest.id]">Clarifications</g:link></li>
    </ul>
</div>

<div align="center" class="subbar">
    <label for="contest">Contest:</label><label for="contestname">${contest.title}</label>
</div>

<div align="center" class="timebar">
    <label for="timetitle">Remaining Time:</label>
    <label id="countdown" for="remtime"></label>
    
</div>