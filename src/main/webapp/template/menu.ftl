<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html lang="en-US">
  <head>
    <title>后台左侧导航页面</title>
	<link rel="stylesheet" type="text/css" href="resources/css/menu.css" />
	<script type="text/javascript" src="resources/js/main.js"></script>
  </head>
  
  <body>
    <ul>
    	<li>
    		<a href="javascript:void(0)" onclick=dis(this) >商品管理</a>
    		<div class="dc">
    			<a href="<@spring.url '/addproduct'/>" target="right">添加商品</a><br/>
    			<a href="<@spring.url '/main.html'/>" target="right">修改商品</a><br/>
	    	</div>
    	</li>
    	
    	<br/><br/>
    	
    </ul>
  </body>