<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>我的个人空间</title>
<style type="text/css">
</style>
</head>
<body>

	<div class="container" id="cj">
		<div class="form-group row form-inline">


			<div class="col">
				审核状态: <select class="custom-select" id="status" name="status">
					<option value="99">所有</option>
					<option value="0">待审核</option>
					<option value="1">已审核</option>
					<option value="-1">未通过</option>
				</select>
			</div>
			<div class="col">
				栏目: <select class="custom-select" id="channel" name="channelId">
					<option value="0">请选择</option>
				</select>
			</div>
			<div class="col">
				分类: <select class="custom-select" id="category" name="categoryId">
					<option value="0">请选择</option>
				</select>

			</div>
			<div class="col">
				<button class="btn btn-info" onclick="query()" id="btn">查询</button>
			</div>
		</div>




		<div>




			<ul class="list-unstyled">

				<c:forEach items="${titles }" var="a">
					<li class="media"><img class="mr-3" src="/pic/${a.picture}"
						alt="no pic" width="200px" height="100px">
						<div class="media-body">

							<dl>
								<dt>
									<a href="/article/selectByAdmin?id=${a.id }" target="_blank">${a.title }</a>
								</dt>
								<dd style="float: right;color: green;">${a.status==0?"待审核":a.status==1?"已审核":"未通过" }</dd>
								<dd>${a.nickname }${a.updated }</dd>
							</dl>

						</div></li>
					<hr>
				</c:forEach>
			</ul>

			${pages }

		</div>

	</div>


</body>

<script type="text/javascript">
$(function(){
    $('.page-link').click(function (e) {
    	  //获取点击的的url
        var url = $(this).attr('data');
    
       //在中间区域显示地址的内容
       $('#cj').load(url);
    });
	
})

</script>
<script type="text/javascript">
	function query() {
		var url = "/article/selectsByAdmin?channelId=" + $("#channel").val()
				+ "&categoryId=" + $("#category").val()+"&status="+$("#status").val()
		$("#content-wrapper").load(url);

	}
//页面加载时
	$(function() {
		
		 //让状态选中
		var status= '${article.status}'
		if(status!=null && status!='')
		$('#status').val(status)
		
		
		//获取文章的栏目ID
		var cid = '${article.channelId}'
		//动态的为下拉库赋值   文章所属栏目
		$.get("/channel/selects", function(obj) {
			for ( var i in obj) {
				$("#channel").append(
						"<option value='"+obj[i].id+"'>" + obj[i].name
								+ "</option>")
			}
			
            //如果用户选择栏目,则让选择栏目默认
			if (cid != null && cid != '') {
				$("#channel").val(cid)
			}
		})

		//当栏目选择框改变时触发
		$("#channel").change(
				function() {
					//获取栏目的ID
					var cid = $(this).val();
					//先清空所有的分类
					$("#category").empty();
					//根据栏目获取分类
					getCategory(cid)
				})
				
			if (cid != null && cid != '') {	
			   getCategory(cid)		
			}

	})
	
	function getCategory(cid){
		$.get("/channel/selectCategorysByCid", {
			cid : cid
		}, function(obj) {
			for ( var i in obj) {
				$("#category").append(
						"<option value='"+obj[i].id+"'>"
								+ obj[i].name + "</option>")
			}
			
			//如果用户选择查询条件,则让查询条件默认
			var catId='${article.categoryId}'
			if(catId!=null && catId!=''){
			$("#category").val(catId);
			}

		})
	}
</script>
</html>