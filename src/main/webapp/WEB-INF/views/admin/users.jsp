<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<link href="/resource/css/all.min.css" rel="stylesheet" type="text/css">
<title>列表</title>
</head>
<body>

	<div class="container-fluid">
		<div class="form-group form-inline">
			<label for="username">用户名</label> <input type="text"
				class="form-control" id="username" name="username"> &nbsp;
			<button class="btn btn-success" type="button" onclick="query()">
				查询</button>
		</div>
		<table class="table  table-hover table-bordered">

			<tr align="center">
				<td>序号</td>
				<td>姓名</td>
				<td>昵称</td>
				<td>性别</td>
				<td>出生日期</td>
				<td>注册日期</td>
				<td>修改日期</td>
				<td>操作</td>
			</tr>


			<c:forEach items="${list }" var="u" varStatus="i">

				<tr align="center">
					<td>${i.index+1 }</td>
					<td>${u.username}</td>
					<td>${u.nickname}</td>
					<td>${u.gender==0?"女":"男"}</td>
					<td>${u.birthday}</td>
					<td><fmt:formatDate value="${u.create_time}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${u.update_time}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
					 <c:if test="${u.locked==0 }">
					 
					  <button class="btn btn-success" onclick="update(${u.id},this)">正常</button>
					 </c:if>
					 <c:if test="${u.locked==1 }">
					   <button class="btn btn-warning"  onclick="update(${u.id},this)">禁用</button>
					 
					 </c:if>
					
					
					</td>
				</tr>

			</c:forEach>



		</table>
		<div>
			
			${pages }

		</div>
	</div>

	<script type="text/javascript">
	
	//更改用户状态
	function update(id,obj){
		//如果当前状态为正常,则要改为禁用,如果为禁用则改为正常
		var locked=$(obj).text()=="正常"?"1":"0";
		
		$.post("/user/update",{id:id,locked:locked},function(flag){
			if(flag){
				
				alert("操作成功");
				//改变按钮内容
				$(obj).text(locked==1?"禁用":"正常");
				//改变样式
				$(obj).attr("class",locked==1?"btn btn-warning":"btn btn-success")
				
				//window.location.reload();
			}
		})
		
		
		
	}
	
		function query() {

			var url = "/user/selects?username=" + $("#username").val();
			//在中间区域加载url
			$("#content-wrapper").load(url)
		}
	</script>
	<!-- 引入公共js. 比如分页的点击事件 会使用 -->
	<script type="text/javascript" src="/resource/js/cms.js"></script>
</body>
</html>