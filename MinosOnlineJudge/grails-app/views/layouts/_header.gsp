<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->
<div id="header">
  <div id="headertitle" align="center">
    <a class="header-main" href="/MinosOnlineJudge/">Minos Online Judge</a>
    <br />
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
        
  <!-- Barra de navegación -->
  <div id="navbar">
    <li><g:link controller="user" action="startpage">Home</g:link></li>
    <li><g:link controller="contest" action="index">Contest</g:link></li>
    <li><a href="#">Help</a></li>
  </div>
</div>

