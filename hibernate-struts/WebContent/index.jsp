<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>部门：<s:property value="#requst.dept.deptName"/></h2>
    <!-- 部门下的员工懒加载 -->
    <table border="1" align="center">
		<tr>
			<td>员工编号</td>
			<td>员工姓名</td>
			<td>员工薪水</td>
		</tr>
       <s:if test="#request.dept.emps!=null">
          <s:iterator var="emp" value="#request.dept.emps">
				<tr>
					<td><s:property value="#emp.empId" /></td>
					<td><s:property value="#emp.empName" /></td>
					<td><s:property value="#emp.salary" /></td>
				</tr>
			</s:iterator>
       </s:if>
       <s:else>
           <tr><td colspan="3">没有员工信息！</td></tr>
       </s:else>
	</table>
</body>
</html>