<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resource/css/all.min.css" rel="stylesheet">
<link href="/resource/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	function addchk(){
		var i = 2;
		$("#chk").append("<input type='text' placeholder='选项"+i+"' name='desc'>")
		i++;
	}
	
	function addvote(){
		$.post("articles/addvote",$("form").serialize(),function(obj){
			if(obj){
				alert("发布成功");
			}else{
				alert("发布失败");
			}
			
		})
		
	}

</script>
</head>
<body>
<button onclick="addchk()">添加选项</button><button onclick="addvote()">发起投票</button>
<form action="">
	<div>
		标题:<input type="text" name="title">
	</div>
	
	<div id="chk">
		<input type="text" placeholder="选项1" name="desc">
	</div>

</form>


</body>
</html>