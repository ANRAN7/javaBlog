<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 使用struts标签 -->
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <!--  获得user的属性值 -->
     <s:property value="userName"/>登陆成功
     <br>
    <!--  获得对应域的属性值 -->
     ${requestScope.name1 }<br>
     ${requestScope.name4 }<br>
     ${sessionScope.name3  }<br>
     ${applicationScope.name2 }<br>
  </body>
</html>
