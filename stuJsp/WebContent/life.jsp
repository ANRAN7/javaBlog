<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! 
  //记录执行次数：
  private int initVar=0;
  private int serviceVar=0;
  private int destroyVar=0;
%>
<%!
  public void jspInit(){
    initVar++;
    System.out.println("jspInit(): JSP被初始化了"+initVar+"次");
  }
  public void jspDestroy(){
    destroyVar++;
    System.out.println("jspDestroy(): JSP被销毁了"+destroyVar+"次");
  }
%>
<%
  serviceVar++;//
  System.out.println("_jspService(): JSP共响应了"+serviceVar+"次请求");
  //对各个的执行次数计数
  String content1="初始化次数 : "+initVar;
  String content2="响应客户请求次数 : "+serviceVar;
  String content3="销毁次数 : "+destroyVar;
%>
<%-- 输出显示 --%>
<h1><%=content1 %></h1>
<h1><%=content2 %></h1>
<h1><%=content3 %></h1>
</body>
</html>