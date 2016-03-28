<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
<title>商品首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<@spring.url'/resources/css/detail.css'/>" />
<script type="text/javascript" src="<@spring.url'/resources/js/main.js'/>"></script>
</head>
<body>
	<div id="container">

		<div id="goods">

			<div id="image">
				<img src="<@spring.url '/${product.productImage }'/>" />
			</div>
			<div id="info">
				<li>${product.productId }</li>
				<li>${product.productTitle }</li>
				<li>${product.productDetail }</li>
				<li>${product.productPrice }</li>
				<li>${product.productSummary }</li>
				<#if !product.isSell >
				<li>已售出</li>
				<#else>
				<li>未出售</li>
				</#if>
				<#if user?exists && user.userType==1>
					<li>
						<a href="javascript:void(0)" onclick=del("<@spring.url '/deleteproduct/${product.productId }'/>")>删除</a>
						<a href="<@spring.url '/editproduct/${product.productId }'/>">修改</a>
					</li>
				<#else>
					<li>
						<a href="<@spring.url '/buyproduct/${product.productId }'/>">购买</a>
					</li>
				</#if>
			</div>

		</div>

	</div>
</body>
</html>
