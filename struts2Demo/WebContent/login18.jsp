<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <!-- i18n -->
    <title><s:text name="title"></s:text></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
  	 <s:form method="post" action="userinter_login.action">
  	 	<s:textfield name="admin.username" key="username" />
  	 	<s:textfield name="admin.pwd" key="pwd" />
  	 	<s:submit key="submit"/><br/>
  	 </s:form>
  </body>
</html>
