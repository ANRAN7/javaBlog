<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
  	 <form method="post" action="${pageContext.request.contextPath }/userinter_login.action">
  	 	用户名：<input type="text" name="admin.username"><br/>
  	 	密码：<input type="text" name="admin.pwd"><br/>
  	 	<input type="submit" value="登陆"><br/>
  	 </form>
  </body>
</html>
