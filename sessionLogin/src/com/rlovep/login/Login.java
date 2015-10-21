package com.rlovep.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应字符
		response.setCharacterEncoding("utf-8");
		//获得名字和密码
		String name=request.getParameter("name");
		
		String pass=request.getParameter("pass");
		//这里应该时判断数据库的
		if("peace".equals(name)&&"pass".equals(pass)){
			//设置名字属性，并跳转到主页
			HttpSession session=request.getSession();
			session.setAttribute("name", name);
			//session.setAttribute("pass", pass);
		   response.sendRedirect("/sessionLogin/index");
		   //url=request.getContextPath()+"/index"   
		}
		else{
			response.sendRedirect(request.getContextPath()+"/fail.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
