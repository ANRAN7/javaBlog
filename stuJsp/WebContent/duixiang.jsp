<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--out对象：对应jspwriter
 --%>
<%
  /* for(int i=0;i<=1024;i++){
	  out.write("a");
	 
  }
System.out.println("当前缓存区大小："+out.getBufferSize());
System.out.println("剩余缓存区大小："+out.getRemaining()); */
//如果不刷新则，123先输出；
//out.flush();
response.getWriter().write("123");
%>
<%--
application对象：对应servlet中的context
存储的属性，是整个应用共享的；同样可以获得配置参数；
 --%>
<%
//存储属性
application.setAttribute("name", "peace");
%>
<%=application.getInitParameter("keys") %>
<%--
config对象：对应servlet中的config
用处不是很大
 --%>
 <%=config.getServletName() %>
 <%--
exception对象：在错误页面中有效，可以获得异常属性
 --%>
 <%--
request对象：对应servlet中的request
 --%>
 <%=request.getLocalName() %>
 <%--
response对象：对应servlet中的response
 --%>
<%
	response.getWriter().println("hello respose");
%>
<%--
session对象：对应servlet中的session
 --%>
 <%
 	session.setAttribute("pass", "567");
 %>
 <%--
pagecontext对象：jsp的页面对象
可以获得其他那个八个域对象：
 --%>
 <%
 	//获得其他对象
    response.getWriter().write("是否相等?"+(out==pageContext.getOut())+"<br/>");
 %>
 <%--可以往不同的域中存对象 --%>
 <%
 	pageContext.setAttribute("message", "wang");
 	pageContext.setAttribute("age", "22", PageContext.REQUEST_SCOPE);
 	pageContext.setAttribute("qq", "374126165", pageContext.SESSION_SCOPE);
 	pageContext.setAttribute("tl","1881679",pageContext.APPLICATION_SCOPE);
 	//重定向到另一个页面取得数据：
 	response.sendRedirect(request.getContextPath()+"/pageget.jsp");
 %>
 
</body>


</html>