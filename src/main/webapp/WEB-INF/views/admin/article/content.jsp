<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>${article.title }</title>
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
	<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<style type="text/css">

</style>
</head>
<body>

	<div class="container" align="center">
	
	  <dl>
	    <dt><button class="btn btn-success" onclick="check(1)">同意</button><button class="btn btn-warning" onclick="check(-1)">不同意</button> <h1>${article.title }</h1></dt>
	    <hr>
	    <dd><div style="float: left"><fmt:formatDate value="${article.updated }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
	    
	     </dd>
	     <br>
	    <dd>${article.content }</dd>
	  
	  
	  </dl>
		
	</div>



<script type="text/javascript">
//审核文章
function check(status){
	var id = '${article.id}';//要审核的文章ID
	$.post("/article/check",{status:status,id:id},function(flag){
		if(flag){
			alert("操作成功!");
			window.close();
		}
		
	})
	
}


</script>
</body>
</html>