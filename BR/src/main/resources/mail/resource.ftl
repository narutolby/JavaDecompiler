<html>
<head></head>
<body>
<div>各位同学好</div>
<br/>
<div style="text-indent:2em ">${courseName}发布资源：<p>
    <ul>
<#list resourceName as fileName>
    <li>${fileName}</li>
</#list>
    </ul>
</p>详细内容请点击<a href="${link}">${link}</a></div>
<br/>
<div>发布时间：${pubDateTime}</div>
</body>
</html>
