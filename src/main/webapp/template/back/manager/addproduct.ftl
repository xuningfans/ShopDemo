<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html lang="en-US">
  <head>
    <title>添加商品</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  
  <body>
    
    <br/><br/>
    <form action="<@spring.url '/addproduct'/>" method="post" enctype="multipart/form-data">
    	<table width="500px">
    	<tr>
    		<td>名称</td>
    		<td><input type="text" name="productTitle" style="width: 200px"></td>
    	<tr>
    	<tr>
    		<td>摘要</td>
    		<td><input type="text" name="productSummary" style="width: 200px"></td>
    	</tr>
    	<tr>
    		<td>售价</td>
    		<td><input type="text" name="productPrice" style="width: 200px"></td>
    	</tr>
    	<tr>
    		<td>图片</td>
    		<td><input type="file" name="productFile" style="width: 200px"></td>
    	</tr>
    	<tr>
    		<td>描述</td>
    		<td><textarea rows="4" cols="40" name="productDetail"></textarea></td>
    	</tr>
    	<tr>
    		<td></td><td><input type="submit" value="添加商品"></td>
    	</tr>
    	</table>
    </form>
    
  </body>
</html>
