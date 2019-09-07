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
<style type="text/css">

</style>
</head>
<body>

	<div class="container" align="center">
	
	  <dl>
	    <dt><h1>${article.title }</h1></dt>
	    <hr>
	    <dd><div style="float: left"><fmt:formatDate value="${article.updated }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
	    
	     </dd>
	     <br>
	    <dd>${article.content }</dd>
	  
	  
	  </dl>
		
	</div>


<script type="text/javascript">
	$(function(){
		$.post("")
		
	})


</script>
</body>
</html>