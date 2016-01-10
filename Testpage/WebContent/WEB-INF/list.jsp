<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页查询数据</title>
</head>
<body>
<table border="1" width="80%" align="center" cellpadding="5" cellspacing="0">
    <tr>
       <td>序号</td>
       <td>员工编号</td>
       <td>员工姓名</td>
       <td>老板id</td>
    </tr>
  <!-- 迭代数据 -->
  <%-- --%>
  <c:choose>
     <c:when test="${not empty requestScope.pageBean.list }">
         <c:forEach var="emp" items="${requestScope.pageBean.list}" varStatus="vs">
          <tr>
            <td>${vs.count }</td>
            <td>${emp.id }</td>
            <td>${emp.empName }</td>
            <td>${emp.bossid}</td>
          </tr>
          </c:forEach>
     </c:when>
     <c:otherwise>
       <tr>
          <td colspan="4">对不起，没有你要的数据</td>
       </tr>
     </c:otherwise>
     </c:choose>
  <%---案件设置 --%>
  <tr>
       <td colspan="4"	align="center">
       当前${requestScope.pageBean.curpage}/${requestScope.pageBean.allPage}页&nbsp;&nbsp;
       <a href="${pageContext.request.contextPath}/index?curpage=1">首页</a>
       <a href="${pageContext.request.contextPath}/index?curpage=${requestScope.pageBean.curpage-1}">上一页</a>
       <a href="${pageContext.request.contextPath}/index?curpage=${requestScope.pageBean.curpage+1}">下一页</a>
       <a href="${pageContext.request.contextPath}/index?curpage=${requestScope.pageBean.allPage}">末页</a>
</table>
</body>
</html>