<%@page import="com.rlovep.lister.SessionBind"%>
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
  	test!
  	<%
  	    //给request域加入属性
  		request.setAttribute("name", "peace");
  	     //新增
  	    request.setAttribute("age", 22);
  	     //移除
  	     request.removeAttribute("age");
  	    //给session对象加入属性，并摧毁
  	    session.setAttribute("session","peace_in_rlovep.com");
  	    //新增
  	    session.setAttribute("author", "peace");
  	    //替换
  	    session.setAttribute("author", "wpeace");
  	    //移除
  	    session.removeAttribute("author");
  	    //对象放入域
  	    session.setAttribute("people", new SessionBind());
  	    //对象移除
  	    session.removeAttribute("people");
  	    session.invalidate();
  	%>
  </body>
</html>
