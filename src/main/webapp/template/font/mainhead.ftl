<!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>用户</title>
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
</head>
<body>
<#include "/inc/title.ftl">
<div class="head">
<span>欢迎[${loginUser.userName}]登录&nbsp;</span>
<input type="button" value="注销" onclick="javascript:window.location.href='logout'">
</div>
</body>
</html>
