<#macro masterTemplate title="Welcome">
    <!DOCTYPE html
            PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
            "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <title>${title}  |  jukeTube</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
    </head>
    <body>
    <center>
    <div class="page">
        <h1>jukeTube</h1>
        <div class="navigation">
        <#if user??>
          <a href="/">Home</a> |
          <a href="/settings">Settings</a> |
          <a href="/logout">Sign out [${user}]</a>
        <#else>
          <a href="/">Home</a> |
          <a href="/login">Settings</a>
        </#if>
        </div>
        <div class="body">
          <#nested />
        </div>
        <div class="footer">
          jukeTube &mdash; Your Remote Player
        </div>
    </div>
    </center>
    </body>
    </html>
</#macro>
