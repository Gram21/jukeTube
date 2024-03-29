<#macro masterTemplate title="Welcome">
    <!DOCTYPE html
            PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
            "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${title}  |  jukeTube</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
    </head>
    <body>
    <center>
    <div class="page">
        <h1>jukeTube</h1>
        <div class="navigation">
        <#if currentUser??>
          <a href="/">Home</a> |
          <a href="/settings/">Controls</a> |
          <a href="/logout/">Sign out [${currentUser}]</a>
        <#else>
          <a href="/">Home</a> |
          <a href="/login/">Controls</a>
        </#if>
        </div>
        <div class="body">
          <#nested />
        </div>
        <br>
        <br>
        <br>
        <div class="footer">
          jukeTube &mdash; Your Remote Player
        </div>
    </div>
    </center>
    </body>
    </html>
</#macro>
