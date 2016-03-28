<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html lang="en-US">
<head>
<title>错误提示</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
</head>
<body> 
	<#if message?exists><h1 class="error">${message}&nbsp;</h1></#if>
</body>
</html>
