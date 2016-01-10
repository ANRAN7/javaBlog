<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下载列表</title>
</head>
<body>
<table border="1" align="center">
     <tr>
     	<th>序号</th>
     	<th>文件名</th>
     	<th>操作</th>
     </tr>
     <c:forEach var="file" items="${requestScope.fileNames }"  varStatus="vs">
            <tr>
                <td>${vs.count }</td>
                <td>${file}</td>
                <td> 
                    <!-- 构建一个地址 -->
                    <c:url var="url" value="UpAndDown">
                      <c:param name="method" value="down"></c:param>
                      <c:param name="fileName" value="${file}"></c:param>
                    </c:url>
                    <!-- 使用上面的地址 -->
                    <a href="${url }">下载</a>
               </td>
               </tr>
     </c:forEach>
</table>
</body>
</html>