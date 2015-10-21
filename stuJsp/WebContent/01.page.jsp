<%@ page 
   language="java" 
   import="java.util.*"
   pageEncoding="utf-8"
   session="true"
  %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>page指令</title>  
  </head>
  
  <body>
      <%
      	 //String name = null;
      	 //name.charAt(1);
      	String name=null;
      	name.equals("sisi");
      	 
      	HttpSession session1 = request.getSession();
      	//session.getId();
       %>
  </body>
</html>
