<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>CMS系统</title>


</head>
<body>
	<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>

	<div>
		<br />
	</div>
	<div class="container">
		<div class="row">
			<!-- 栏目-->
			<div class="col-md-2 ">
				<ul class="list-group">
					<li class="list-group-item  text-center" id="channel"><a
						class="channel" href="/">热门</a></li>

					<c:forEach items="${channels}" var="c">
						<li class="list-group-item text-center" id="channel${c.id }"><a
							class="channel" href="/?channelId=${c.id}">${c.name}</a></li>
					</c:forEach>
				</ul>
			</div>

			<!-- 中间内容主体区 -->
			<div class="col-md-7 split min_h_500">
				<!-- 默认显示图片轮播+热点文章 -->
				<c:if test="${article.channelId ==null }">

					<div id="hot">
						<div id="carousel" class="carousel slide" data-ride="carousel">
								<div class="carousel-inner">
								<div class="carousel-item active">
									<img class="d-block w-100" src="/pic/1.jpg" alt="First slide">
								</div>
								<div class="carousel-item ">
									<img class="d-block w-100" src="/pic/2.jpg" alt="Second slide">
								</div>
								<div class="carousel-item">
									<img class="d-block w-100" src="/pic/3.jpg" alt="Third slide">
								</div>
							</div>
							<a class="carousel-control-prev" href="#carousel" role="button"
								data-slide="prev"> <span class="carousel-control-prev-icon"
								aria-hidden="true"></span> <span class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carousel" role="button"
								data-slide="next"> <span class="carousel-control-next-icon"
								aria-hidden="true"></span> <span class="sr-only">Next</span>
							</a>
						</div>
						<br>
						<!-- 新闻热点 -->
							
						<c:forEach items="${hotArticles}" var="c">
						<c:if test="${c.contentType!=2 }">
							<div class="media">
								<img class="align-self-start mr-3" src="/pic/${c.picture }"
									alt="no pic" width="200px" height="100px">
								<div class="media-body">
									<h5 class="mt-0">
										<a href="/article/select?id=${c.id }" target="_blank">${c.title }</a>
									</h5>
									<p class="blog_item_footer">
										<span class="glyphicon glyphicon-user" title="作者"></span>作者：${c.username}&nbsp;&nbsp;
										&nbsp; <span class="glyphicon glyphicon-time" title="发布时间"></span>发布：&nbsp;
										${c.updated }&nbsp;&nbsp;&nbsp;&nbsp;
									</p>
								</div>
							</div>
							<br />
							
							</c:if>
						</c:forEach>
						${pages }
					</div>

				</c:if>

				<c:if test="${article.channelId!=null }">
					<!-- 分类 -->
					<div id="category">
						<ul class="nav">
							<!--栏目下所有 分类 -->
							<li class="nav-item" id="cat"><a class="nav-link"
								href="/?channelId=${article.channelId}">全部</a></li>
							<c:forEach items="${categorys}" var="c">
								<li class="nav-item" id="cat${c.id }"><a class="nav-link"
									href="/?channelId=${c.channelId}&categoryId=${c.id}">${c.name }</a></li>
							</c:forEach>
						</ul>

					</div>
					<br />

					<!-- 文章 -->

					<div id="article">
						<c:forEach items="${articles}" var="c">
							<div class="media">
						
								<img class="align-self-start mr-3" src="/pic/${c.picture }"
									alt="no pic" width="80px" height="80px">
								<div class="media-body">
									<h5>
										<a href="javascript:detail(${c.id },${c.channel_id })">${c.title }</a>
									</h5>
									<p class="blog_item_footer">
										<span class="glyphicon glyphicon-user" title="作者"></span>作者：${c.username}&nbsp;&nbsp;
										&nbsp; <span class="glyphicon glyphicon-time" title="发布时间"></span>发布：&nbsp;
										${c.updated }&nbsp;&nbsp;&nbsp;&nbsp;
									</p>
								</div>
							
							</div>
							<br />
						</c:forEach>
						
						${pages }


					</div>

				</c:if>

			</div>
           <!-- 最新文章 -->
			<div class="col-md-3">
				<div class="card">
					<div class="card-header">最新文章</div>
					<div class="card-body">
						<ol>
							<c:forEach items="${lasts}" var="article">
							<c:if test="${article.contentType!=2 }">
								<li class="text-truncate"><a
									href="/article/select?id=${article.id }" target="_blank">${article.title}</a></li>
								</c:if>	
							</c:forEach>
						</ol>
					</div>
				</div>
				<div class="card">
					<div class="card-header">精品图片集</div>
					<div class="card-body">
					<c:forEach items="${images}" var="p">
					<c:if test="${p.contentType==2 }">
					<a href="/article/selectPic?id=${p.id }">
					 <img alt="" src="/pic/${p.picture}" width="50px" height="50px">
					 </a>
					 </c:if>
					 </c:forEach>
					</div>
				</div>
				
				   <!-- 发起的投票 -->
				<div class="card">

					<div class="card-header">发起的投票</div>
					<c:forEach items="${votes}" var="m">
					<c:if test="${m.contentType==3 }">
						<div class="card-body">
							<a href="/article/selectVote?id=${m.id }" target="_blank">
								${m.title } </a>
								</div>
								</c:if>
					</c:forEach>

				</div>



			</div>
				
				

			</div>
		</div>
	</div>
<div class="row">
   <div class="col" align="center">
     <c:forEach items="${links }" var="l">
     
        
         <a href="${l.url}" target="_blank"> ${l.text }    </a>
 
     
     </c:forEach>
   
   </div>

</div>
	<br />

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script type="text/javascript">
		//为左侧频道绑定点击事件
		$(function() {

			//为栏目添加高亮的样式
			$("#channel${article.channelId}").addClass(
					'list-group-item-warning');
			$("#cat${article.categoryId}").addClass('list-group-item-warning');

			//分页的点击事件

			$('.page-link').click(function(e) {

				//获取点击的的url
				var url = $(this).attr('data');
				//alert(url)
				// console.log(url);
				//在中间区域显示地址的内容
				$('#article').load(url);
			  location.href=url;
			});

		})
		
		
		
	</script>
</body>
</html>