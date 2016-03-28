<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html lang="en-US">
  <head>
    <title>添加商品</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/main.css'/>" />
	<script type="text/javascript" src="<@spring.url '/resources/js/main.js'/>"></script>
  </head>
  
  <body>
    
    <br/><br/>
    <form action="<@spring.url '/editproduct/${product.productId}'/>" method="post" enctype="multipart/form-data">
    	<table width="500px" border=1>
    	<tr>
    		<td>名称</td>
    		<td><input type="text" name="productTitle" style="width: 200px" <#if product?exists>value="${product.productTitle}"</#if>></td>
    	<tr>
    	<tr>
    		<td>摘要</td>
    		<td><input type="text" name="productSummary" style="width: 200px" <#if product?exists>value="${product.productSummary}"</#if>></td>
    	</tr>
    	<tr>
    		<td>售价</td>
    		<td><input type="text" name="productPrice" style="width: 200px" <#if product?exists>value="${product.productPrice}"</#if>></td>
    	</tr>
    	<tr>
    		<td>图片</td>
    		<td><input type="file" name="productFile" style="width: 200px" ></td>
    	</tr>
    	<tr>
    		<td>描述</td>
    		<td><textarea rows="4" cols="40" name="productDetail" ><#if product?exists>${product.productDetail}</#if></textarea></td>
    	</tr>
    	<tr>
    		<td></td><td><input type="submit" value="修改商品"></td>
    	</tr>
    	</table>
    </form>
    
  </body>
</html>
