<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://rlovep.com" prefix="rlovep" %><!-- 使用自定义标签库 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 测试简单的自定义标签，标签体(我是你)不显示 --%>
<rlovep:showIp>我是你
</rlovep:showIp>
<hr/>
<!-- 测试带属性的标签，标签体显示通过类处理 -->
<rlovep:AttributeTags name="peace" value="123456">
<br/>
wang
</rlovep:AttributeTags>
<!-- 测试choose -->
<rlovep:choose>
	<rlovep:When test="${10<5 }">
	   条件成立执行when
	</rlovep:When>
	<rlovep:otherwise>
	   条件不成立执行otherwise
	</rlovep:otherwise>
</rlovep:choose>
</body>
</html>