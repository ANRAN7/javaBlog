<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body> 
    <form action="${pageContext.request.contextPath}/emp/addAll.action" method="POST">
    	<table border="2" align="center">
    		<caption><h2>批量注册员工</h2></caption>
    		<tr>
    			<td><input type="text" name="empList[0].username" value="哈哈"/></td>
    			<td><input type="text" name="empList[0].salary" value="7000"/></td>
    		</tr>
    		<tr>
    			<td><input type="text" name="empList[1].username" value="呵呵"/></td>
    			<td><input type="text" name="empList[1].salary" value="7500"/></td>
    		</tr>
    		<tr>
    			<td><input type="text" name="empList[2].username" value="班长"/></td>
    			<td><input type="text" name="empList[2].salary" value="8000"/></td>
    		</tr>
    		<tr>
    			<td><input type="text" name="empList[3].username" value="键状哥"/></td>
    			<td><input type="text" name="empList[3].salary" value="8000"/></td>
    		</tr>
    		<tr>
    			<td><input type="text" name="empList[4].username" value="绿同学"/></td>
    			<td><input type="text" name="empList[4].salary" value="9000"/></td>
    		</tr>
    		<tr>
    			<td colspan="2" align="center">
    				<input type="submit" value="批量注册"/>
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
