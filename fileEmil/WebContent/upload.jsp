<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>upload</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>	
  	 <form name="frm_test" action="${pageContext.request.contextPath }/UpAndDown?method=upload" method="post" enctype="multipart/form-data">
  	 	 <%--<input type="hidden" name="method" value="upload">--%>
  	 	 
  	 	 用户名：<input type="text" name="userName">  <br/>
  	 	文件：   <input type="file" name="file_img">   <br/>
  	 	
  	 	<input type="submit" value="提交">
   	 </form>
  </body>
</html>
