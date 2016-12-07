
<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Login">
    <h2>Login</h2>
    <#if error??>
      <div class="error">
        <strong>Error:</strong> ${error}
      </div>
    </#if>
    <br>
    <div class="container">
      <form action="/login/" method="post">
        <input type="text" name="username" maxlength="42"><br>
        <input type="password" name="password" maxlength="42"><br>
        <input type="submit" value="Login">
      </form>
    </div>
</@layout.masterTemplate>
