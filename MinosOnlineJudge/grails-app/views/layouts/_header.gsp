<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->
<div id="header">
  <p><a class="header-main" 
        href="/MinosOnlineJudge/">Minos Online Judge</a></p>
        <p class="header-sub">It's time trial</p>
        <g:if test="${session?.user}">
          <label for="showUser">${session?.user?.username} | </label>
          <g:link controller="user" action="logout">Logout</g:link>
        </g:if>
        <g:else>
          <g:link controller="user" action="login">Login</g:link>
          <label for="showUser"> | </label>
          <g:link controller="user" action="signup">Register</g:link>
        </g:else>
</div>
