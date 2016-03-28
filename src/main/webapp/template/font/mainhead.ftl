<!DOCTYPE HTML>
<html lang="en-US">
<head>
<title>用户</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
<script type="text/javascript" src="resources/js/main.js"></script>
<script type="text/javascript">
	function reload() {
		window.top.left.location.href="menu";
		window.top.right.location.href="main";
	}
</script>
</head>
<body onload="reload()">
<#include "/inc/title.ftl">
<div class="head">
<span>欢迎[${loginUser.userName}]登录&nbsp;</span>
<input type="button" value="注销" onclick="javascript:window.location.href='logout'">
</div>
</body>
</html>
