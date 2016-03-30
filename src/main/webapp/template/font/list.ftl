<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
  <head>
    <title>商品首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/main.css'/>" />
	<script type="text/javascript" src="resources/js/main.js"></script>
</head>
<body>
  	<div id="container">
  		<div id="main">
  			<div id="goods">
  				<#list products as product>
	  				<div id="product">
	  					<div id="image">
	  						<img src="<@spring.url '/${product.productImage }'/>"/>
	  					</div>
	  					<div id="info">
	  						<li>${product.productId }</li>
	  						<li>${product.productTitle }</li>
	  						<li>${product.productDetail }</li>
	  						<li>${product.productPrice }</li>
	  						<li>${product.productSummary }</li>
	  						<#if !product.isSell ><li>已售出</li><#else><li>未出售</li></#if>
	  						<#if user?exists && user.userType==1>
							<li>
								<a href="<@spring.url '/detail/${product.productId }'/>">详情</a>
								<a href="<@spring.url '/editproduct/${product.productId }'/>">修改</a>
							</li>
							<#else>
	  						<li>
	  							<a href="<@spring.url '/detail/${product.productId }'/>">详情</a>
	  							<a href="<@spring.url '/buyproduct/${product.productId }'/>">购买</a>
	  						</li>
	  						</#if>
	  					</div>
	  					<div style="clear: both"></div>
	  				</div>
	  				<#if (product_index+1)%3==0>
	  					<div style="clear: both"></div>
	  					<br/>
	  				</#if>
  				</#list>
  				<div style="clear: both"></div>
  				<br/>
  			</div>
  		</div>
   	</div>
  </body>
</html>
