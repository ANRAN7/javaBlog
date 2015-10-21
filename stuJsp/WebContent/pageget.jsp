<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获得属性</title>
</head>
<body>
     page域：<%=pageContext.getAttribute("message",PageContext.PAGE_SCOPE) %><br/>
    request域： <%=pageContext.getAttribute("age",PageContext.REQUEST_SCOPE) %><br/>
    session域： <%=pageContext.getAttribute("qq",PageContext.SESSION_SCOPE) %><br/>
    context域：<%=pageContext.getAttribute("tl",PageContext.APPLICATION_SCOPE) %><br/>
</body>
</html>