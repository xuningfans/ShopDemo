<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
</head>
<body>
<#include "/inc/title.ftl">
<form action="/livestore/login" method="post"  class="head">
	<#if message?exists><span class="error">${message}&nbsp;</span></#if>
	UserName: <input type="text" name="userName" <#if userName?exists>value="${userName}"</#if>/>
	Password: <input type="password" name="userPassword" />
	<input type="submit" value="Submit" />
</form>
</body>
</html>
