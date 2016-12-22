<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>淘淘solr搜索系统</title>
</head>
<body>
	<div style="text-align: center;margin-top:80px">
		<font color="#AAA" size="16px">solr搜索系统正常运行中<span id="spanId"></span></font>
	</div>
</body>
<script>
var i = 1;
function running(){
	var txt = "";
	var span = document.getElementById("spanId");
	for(var j = 0; j < i%4; j++){if(i%4==0){txt="";}txt += ".";}
	i++;span.innerText = txt;
}window.setInterval(running,1000);
</script>
</html>