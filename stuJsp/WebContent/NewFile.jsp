<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
  for(int i=0;i<5;i++)
  {
    out.print("hello peace"+i);
%>
  <br/>
 <%
   out.print("换行符穿插在中间了,一样会被循环输出");
  }//for循环结束
 %>
 <%! private int i=10; %> 
 <%!
    public void test(){
	 int a=0;
	 int b=2;
	 a=a+b;
	 System.out.print(a);
 }
 %>
</body>
</html>