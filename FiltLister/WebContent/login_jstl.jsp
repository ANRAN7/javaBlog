<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--引入jstl国际化与格式化标签库 --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<!-- 一、设置本地化对象 -->
  	<fmt:setLocale value="${pageContext.request.locale}"/>
  	<!-- 二、设置工具类 -->
  	<fmt:setBundle basename="cn.itcast.f_i18n.msg" var="bundle"/>

    <title><fmt:message key="title" bundle="${bundle}"></fmt:message></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
  <form name="frmLogin" action="${pageContext.request.contextPath }/admin?method=login" method="post">
  	<table align="center" border="1">
  		<tr>
  			<td><fmt:message key="username" bundle="${bundle}"></fmt:message></td>
  			<td>
  				<input type="text" name="userName">
  			</td>
  		</tr>
  		<tr>
  			<td><fmt:message key="pwd" bundle="${bundle}"></fmt:message></td>
  			<td>
  				<input type="password" name="pwd">
  			</td>
  		</tr>
  		<tr>
  			<td>
  				<input type="submit" value="<fmt:message key="submit" bundle="${bundle}"/>">
  			</td>
  		</tr>
  	</table>
  </form>
  </body>
</html>
