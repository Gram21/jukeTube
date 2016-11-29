
<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Home">
    <h2>${pageTitle}</h2>
    <#if currentlyPlaying??>
      <div class="currentlyPlaying">
        <strong>Currently Playing:</strong> ${currentlyPlaying}
      </div>
    </#if>
    <#if error??>
      <div class="error">
        <strong>Error:</strong> ${error}
      </div>
    </#if>
</@layout.masterTemplate>
