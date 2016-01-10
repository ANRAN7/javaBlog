<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论</title>
<!-- 引入ckeditor组件(给用户输入提供方便) --> 
	<script src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/ckeditor/samples/sample.css">
</head>
<body>
   <!-- 获得评论 -->
   ${applicationScope.content }
   <form action="${pageContext.request.contextPath }/dis" method="post" name="frmDis">
   
      发表评论：<textarea rows="6" cols="30" class="ckeditor" name="content"></textarea>
      <input type="submit" value="发表评论"/>
   </form>
</body>
</html>