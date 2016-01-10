<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登陆</title>
</head>
<body>
<!-- 注意提交的动作需要为hello -->
<form action="${pageContext.request.contextPath }/user/login" method="get">
   用户名：<input type="text" name="name" /><br/>
   密码:<input type="password" name="pass"/>
  <input type="submit" value="提交"/>
</form>
</body>
</html>