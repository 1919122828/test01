<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resource/css/all.min.css" rel="stylesheet" type="text/css">
<link href="/resource/css/bootstrap.min.css" rel="stylesheet" >
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	function toadd(){
		
		$("#content-wrapper").load("/links/toadd");
		
	}


</script>
</head>
<body>

<div id="cj">
<table class="table">
	<tr>
		<td>#</td>
		<td>urlname</td>
		<td>url</td>
		<td>created</td>
		<td>[操作]
		
		<button onclick="toadd()">添加</button>
		
		</td>
	</tr>
	
		<c:forEach items="${list }" var="l">
			<tr>
				<td>${l.id }</td>
				<td>${l.text }</td>
				<td>${l.url }</td>
				<td>
					<fmt:formatDate value="${l.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		
		</c:forEach>

</table>
</div>
</body>
</html>