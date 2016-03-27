function dis(node) {
	var target = node.nextSibling;
	while (target.nodeType != 1) {
		target = target.nextSibling;
	}
	target.style.display = target.style.display == 'block' ? 'none' : 'block';
}

function del(myUrl) {
	var r = window.confirm("你确定删除吗？");
	if(r){
		this.location.href=myUrl;
    }
}