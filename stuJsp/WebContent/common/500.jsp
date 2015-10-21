<%@ page language="java" pageEncoding="utf-8" isErrorPage="true"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>500处理页面</title>  
  </head>
  
  <body>
    500错误！系统正在抢修中...<br/>
    错误原因： <%=exception.getMessage() %>
  </body>
</html>
