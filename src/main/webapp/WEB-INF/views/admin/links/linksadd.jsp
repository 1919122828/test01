<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resource/css/all.min.css" rel="stylesheet" type="text/css">
<link href="/resource/css/bootstrap.min.css" rel="stylesheet" >
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src=""></script>
</head>
<body>


<div class="container-fluid">
 <span style="color: red">${error }</span>
		<form action="" id="form1">
			<table class="table">
				<tr>
					<td>链接名称:<input type="text" name="text"></td>
				</tr>
				<tr>
					<td>url:<input type="text" name="url"></td>
				</tr>
				<tr>
					<td><button type="button" class="btn btn-info" onclick="save()">保存</button></td>
				</tr>
			</table>

		</form>


	</div>
	<!-- 引入公共js. 比如分页的点击事件 会使用 -->
	<script type="text/javascript" src="/resource/js/cms.js"></script>
	
	<script type="text/javascript">
	function save(){
		$.post("/links/save",$("#form1").serialize(),function(flag){
			
			if(flag){
				alert("保存成功")
			}
		})
		
		
		
	}
	
	
	</script>

</body>
</html>