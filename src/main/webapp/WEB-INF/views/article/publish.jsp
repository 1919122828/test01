<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>KindEditor JSP</title>

<script>
	KindEditor.ready(function(K) {
		window.editor1 = K.create('textarea[name="content1"]', {
			cssPath : '/resource/kindeditor/plugins/code/prettify.css',
			uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
	function query() {
		alert(editor1.html())
		//alert( $("[name='content1']").attr("src"))
	}
</script>
</head>
<body>
	<%=htmlData%>
	<form id="form1">

		<div class="form-group">
			<label for="title">标题</label> <input type="text" class="form-control"
				name="title" id="title">
		</div>


		<div class="form-group">

			<textarea name="content1" cols="100" rows="8"
				style="width: 840px; height: 250px; visibility: hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		</div>

		<div class="form-group">
			<label for="file">标题图片</label> <input type="file"
				class="form-control" name="file" id="file">
		</div> 
		<div class="form-group row">

			<div class="col">
				<label for="channel">栏目</label>
				 <select class="custom-select"	id="channel" name="channelId">
				 <option value="">请选择</option>
				</select>
			</div>
			<div class="col">
				<label for="category">栏目分类</label>
				 <select class="custom-select"
					id="category" name="categoryId">
				</select>
			</div>

		</div>


		<br /> <input type="button" name="button" class="btn btn-info"
			value="提交内容" onclick="publish()" />
	</form>
	
	
	<script type="text/javascript">
	  //发布文章
	  function publish(){
		
		//序列化表单数据带文件
		var formData = new FormData($( "#form1" )[0]);
		
		  //封装富文本编辑器中的内容
		formData.set("content",editor1.html()) 
		  
		  $.ajax({
			  type:"post",
			  url:"/article/publish",
			  data:formData,
			// 告诉jQuery不要去处理发送的数据
			  processData : false,
			  // 告诉jQuery不要去设置Content-Type请求头
			  contentType : false,
			  success:function(flag){
				  if(flag){
						alert("发布成功");
					 }else{
						 alert("发布失败"); 
					 }  
			  }
			  
			  
		  })
		
		  
	  }
	
	
	
	
	
	$(function(){
		//动态的为下拉库赋值   文章所属栏目
		$.get("/channel/selects",function(obj){
			for(var i in obj){
				$("#channel").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>")
			}
		})
		
		//当栏目选择框改变时触发
		$("#channel").change(function(){
			//获取栏目的ID
			var cid =$(this).val();
			//先清空所有的分类
			$("#category").empty();
			$.get("/channel/selectCategorysByCid",{cid:cid},function(obj){
				for(var i in obj){
				$("#category").append("<option value='"+obj[i].id+"'>"+obj[i].name+"</option>")
				}
				
				
			})
			
			
		})
		
	})
	
	
	</script>
	
	
</body>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>