<%@page import="java.util.Random"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>hello jsp</title>
</head>
<body>
  1.jsp注释：<br/>
  <%-- 这些注释不会出现在html的源码中 可以用来注释jsp的代码--%>
	<%
		//写java代码
		SimpleDateFormat sdf=new SimpleDateFormat();
		String curdate=sdf.format(new Date());
		out.write("当前时间为："+curdate);
	%><hr/>
	2.jsp声明：<hr/>
	<%!
		//变量声明    转化为servlet时变为成员变量
		public String name="peace";
		//函数声明    转化为servlet的成员方法
		public String getName(){
			
			return name;//可以调用成员变量和方法
		}
	%>
	<%--在脚本处声明的变量是局部变量不能带有修饰符 --%>
	<%
	  String nick="sisi";
	  int a=10,b=10;
	%>
	3.jsp表达式：<br/>
	<%--表达式可以输出相当于out.write 不需要分号结束--%>
	  <%=(a-b) %>
	  <%=getName() %>
	  <hr/>
	4.jsp脚本：<br/>
	<%
        Random ran=new Random(20);
		float num=ran.nextFloat();
	%>
	随机小数：<%=num %>
	<hr/>
	<!-- 穿插html代码 -->
	<%
		for(int i=1;i<=5;i++){	
	%>
	 <h<%=i %>>标题<%=i %></h<%=i %>>
	 <%
		}
	 %>
	 <hr/>
	 <!-- 显示99乘法表 -->
	 <%
	 	for(int i=1;i<9;i++){
	 		for(int j=1;j<=i;j++){
	 		 %>	
	 		 <%=(i) %> *<%=j %>=<%=i*j %>&nbsp;
	 		 <%
	 		   }
	 		 %>
	 		 <br/>
	 	<% 
	 	}%>
	
</body>
</html>