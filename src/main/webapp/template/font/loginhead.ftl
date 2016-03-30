<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="resources/css/main.css" />
<script type="text/javascript" src="resources/js/main.js"></script>
<script type="text/javascript">
	function login() {
		document.getElementById("login").submit();
	}
	function reload() {
		window.top.left.location.href="menu";
		window.top.right.location.href="list";
	}
</script>
</head>
<body onload="reload()">
<#include "/inc/title.ftl">
<form id="login" action="<@spring.url '/login'/>" method="post"  class="head">
<#--<#if user??>  
	    <@spring.bind "user.userName" />
	    <@spring.bind "user.userPassword" />
	    <@spring.showErrors "<br>"/>  
	</#if>-->   
	<#if message?exists><span class="error">${message}&nbsp;</span></#if>
	UserName: <input type="text" name="userName" <#if userName?exists>value="${userName}"</#if>/>
	Password: <input type="password" name="userPassword" />
	<input type="submit" value="登录" onclick=login()/>
</form>
</body>
</html>
