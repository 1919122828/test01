<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>欢迎注册</title>


<style type="text/css">
.form-inline label {
	display: inline-block;
}
</style>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
	<div class="container">
		<div class="row passport_bg">
			<div class="col-md-6">
				<div class="passport_panel">
					<div class="card">
						<div class="card-header">欢迎登陆</div>
						<div class="card-body">
						  <div align="center">  <span style="color: red">${error }</span></div>
							 <form:form modelAttribute="userVO" id="validatorForm" action="/passport/login" method="post">
							
								<div class="form-group">
									<label for="username">用户名</label>

									<form:input path="username" class="form-control" id="username" />
									<form:errors path="username" cssStyle="color:red"/>
									
								</div>

								<div class="form-group">
									<label for="password">密码</label>
									<form:password path="password" class="form-control" id="password" />
									<form:errors path="password" cssStyle="color:red"/>
								</div>

								

								<button type="submit" class="btn btn-success">登陆</button>

							</form:form> 





						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="passport_r">
					<h3>最新加入的用户：</h3>
					<p align="center">
						<img src="/resource/images/guest.jpg" alt="..."
							class="rounded-circle img-thumbnail" /> &nbsp;&nbsp;&nbsp;&nbsp;
						<img src="/resource/images/guest1.jpg" alt="..."
							class="rounded-circle img-thumbnail" />
					</p>
				</div>
			</div>
		</div>
	</div>
	<div>
		<br /> <br />
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<script type="text/javascript">
	$(function() {
			//通过jquery.validator对表单校验
			$("#validatorForm").validate({

				//定义校验规则
				rules : {
					username :{
						required	:true,
						
					},
					password:{
						required	:true,
						
					},
					
				

				},//不符合规则的消息提示
				messages : {
					username : {
						required:"用户名不能为空 !",
						
						
					},
					password:{
						required:"密码不能为空 !",
						
					},
					
                     
				}

			})
		}) 
	</script>

</body>
</html>