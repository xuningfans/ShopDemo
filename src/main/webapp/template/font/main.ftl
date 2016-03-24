<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
  <head>
    <title>商品首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css" />
</head>
<body>
  	<div id="container">
  		<div id="main">
  			<div id="goods">
  				<#list products as product>
	  				<div id="product">
	  					<div id="image">
	  						<img src="#"/>
	  					</div>
	  					<div id="info">
	  						<li>${product.productId }</li>
	  						<li>${product.productTitle }</li>
	  						<li>${product.productImage }</li>
	  						<li>${product.productDetail }</li>
	  						<li>${product.productPrice }</li>
	  						<li>${product.productSummary }</li>
	  						<#if product.isSell ><li>已售出</li><#else><li>未出售</li></#if>
	  						<li>
	  							<a href="<@spring.url '/deleteproduct?id=${book.id }'/>">购买</a>
	  						</li>
	  					</div>
	  					<div style="clear: both"></div>
	  				</div>
	  				<#if product_index%3==0>
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
