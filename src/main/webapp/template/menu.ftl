<#--import "/spring.ftl" as spring /-->
<!DOCTYPE HTML>
<html>
  <head>
    <title>后台左侧导航页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="resources/css/menu.css" />
	<script type="text/javascript" src="resources/js/main.js"></script>
  </head>
<body>
  <#if user?exists && user.userType==1>
    <ul>
    	<li>
    		<a href="javascript:void(0)" onclick=dis(this) >管理菜单</a>
    		<div class="dc">
    			<a href="<@spring.url '/list.html'/>" target="right">商品列表</a><br/>
    			<a href="<@spring.url '/addproduct'/>" target="right">添加商品</a><br/>
    			<a href="<@spring.url '/listorders.html'/>" target="right">订单管理</a><br/>
	    	</div>
    	</li>
    	
    	<br/><br/>
    </ul>
  <#else>    	
    <ul>
    	<li>
    		<a href="javascript:void(0)" onclick=dis(this) >用户菜单</a>
    		<div class="dc">
    			<a href="<@spring.url '/list.html'/>" target="right">全部商品</a><br/>
    			<a href="<@spring.url '/list.html'/>" target="right">已购商品</a><br/>
    			<a href="<@spring.url '/list.html'/>" target="right">未购商品</a><br/>
    			<a href="<@spring.url '/listorders.html'/>" target="right">订单管理</a><br/>
	    	</div>
    	</li>
    	<br/><br/>
    </ul>
  </#if>	
 </body>
 </html>