<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
  <head>
    <title>订单列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="resources/css/main.css" />
	<script type="text/javascript" src="resources/js/main.js"></script>
  </head>
  
  <body style="text-align: center">
   	<br/><br/>
   	<table frame="border" cellpadding="0" cellspacing="0" width="90%">
   		<caption><h2>订单信息</h2></caption>
   		<tr>
   			<td>订单人</td>
   			<td>订单编号</td>
   			<td>订单状态</td>
 			<td>订单总价</td>
 			<td>操作</td>
   		</tr>

			<#list orders as order>
				<tr>
					<td>
						${order.user.userName }
					</td>
					<td>
						${order.ordersId }
					</td>
					<td>
						${order.state.state}
					</td>
					<td>
						${order.ordersPrice }
					</td>
					<td>
						<a href="#">查看明细</a>
						<a href="#">修改</a>
					</td>
				</tr>
			</#list>
		</table>
  </body>
</html>