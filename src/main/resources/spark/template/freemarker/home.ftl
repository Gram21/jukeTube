
<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Home">
    <h2>Home</h2>
    <#if error??>
      <div class="error">
        <strong>Error:</strong> ${error}
      </div>
    </#if>

      <div class="currentlyPlaying">
        <strong>Currently Playing:</strong>
        <#if currentlyPlaying??>
        ${currentlyPlaying.title}
        <#else>None
        </#if>
      </div>
    <br>
    <div class="container">
      <form action="/" method="post">
        <input type="text" name="song_link" maxlength="43"><input type="submit" value="Add to Playlist">
      </form>
    </div>
</@layout.masterTemplate>
