package com.rlovep.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Index() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应字符
		response.setCharacterEncoding("utf-8");
		//获得输出流
		PrintWriter out = response.getWriter();
		
		String html=" ";
		//获得session：判断session是否存在，不存在则跳转到登陆页面
		HttpSession session=request.getSession();
		if(session==null){
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		//获得名字属性，不存在名字证明没登陆，跳转到登陆页面
		String name=(String)session.getAttribute("name");
		if(name==null)
		{
			response.sendRedirect(request.getContextPath()+"/login.html");
			return;
		}
		//输出html
		html = "<html><body>欢迎回来，"+name+"，<a href='"+request.getContextPath()+"/LoginOut'>安全退出</a></body></html>";
		
		
		out.write(html);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
