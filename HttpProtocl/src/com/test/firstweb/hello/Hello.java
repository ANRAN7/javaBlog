package com.test.firstweb.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet(name = "Hello2", urlPatterns = { "/Hello2" })
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		  response.setCharacterEncoding("UTF-8");
		  response.setContentType("text/html");
		  
		  PrintWriter out = response.getWriter();
		  
		  /* 输出到客户端 */
		  out.println("<html>");
		  out.println("<head><title>Servlet test</title></head>");
		  out.println("<body>");
		  out.println("<form action = '" + request.getRequestURI() + "' method = 'post'>");
		  out.println("请输入您的名字：<input type = 'text' name = 'name' />");
		  out.println("<input type = 'submit' />");
		  out.println("</form>");
		  
		  String name = request.getParameter("name");
		  
		  if( (name != null) && (name.trim().length() > 0) )
		  {
		   out.println("您好， <b>" + name + "</b>. 欢迎来到Java Web世界！");
		  }
		  
		  out.println("</body>");
		  out.println("</html>");  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
