package com.rlovep.serlvet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestRedect
 */
@WebServlet("/TestRedect")
public class TestRedect extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 保存数据到request域对象
		 */
		/*request.setAttribute("name", "rose");
		    //重定向
				*//**
				 * 注意：可以跳转到web应用内，或其他web应用，甚至其他外部域名。
				 *//*
		//request域数据会丢失
		response.sendRedirect("/HttpSer/GetData");
		//重定向到外部域名：
		//response.sendRedirect("www.baidu.com");
*/	
		/**
		 * 保存数据到request域对象
		 */
		request.setAttribute("name", "rose");
		//转发	
		/**
		 * 注意：不能转发当前web应用以外的资源。
		 */
		/*RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/GetDataServlet");
		rd.forward(request, response);*/
		this.getServletContext().
		getRequestDispatcher("/GetData").forward(request, response);
		}
}
