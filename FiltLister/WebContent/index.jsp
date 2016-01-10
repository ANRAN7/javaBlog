<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 是设置基础路径的,basePath为变量，简单的静态网页的话你设置比如：
    <base href="http://www.baidu.com">，那你下面的href属性就会以你上面设的为基准，
    如：<a href="http://www.baidu.com/xxx.htm"></a>你现在就只需要写<a href="xxx.htm"></a> -->
 <%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=<%=basePath %>/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Filter</title>
</head>
<body>
<a href="http://www.baidu.com">百度</a>
<a href="list.jsp">列出所有</a>
<jsp:forward page="/list.jsp"></jsp:forward>
</body>
</html>