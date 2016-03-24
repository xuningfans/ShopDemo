<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html lang="en-US">
  <head>
    <title>后台左侧导航页面</title>
    
    <style type="text/css">
      .dc { 
      		display: none; 
      		margin-left: 10px;
      	  }
	</style>
	
	<script type="text/javascript">
	      function dis(node) {
			var target = node.nextSibling;
			while(target.nodeType!=1){
				target = target.nextSibling;
			}
			target.style.display = target.style.display == 'block' ? 'none' : 'block';
	      }
	</script>
  </head>
  
  <body>
    <ul>
    	<li>
    		<a href="javascript:void(0)" onclick=dis(this) >商品管理
    			<div class="dc">
	    			<a href="<@spring.url '/addproduct'/>" target="right">添加商品</a><br/>
	    			<a href="<@spring.url '/addproduct'/>" target="right">修改商品</a><br/>
	    		</div>
    		</a>
    	</li>
    	
    	<br/><br/>
    	
    </ul>
  </body>