package com.rlovep.dis;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisServlet
 */
@WebServlet("/dis")
public class DisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletContext context = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisServlet() {
        super();
               // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
    	context=config.getServletContext();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求数据
		String text=request.getParameter("content");
		String content=null;
		/**
		 * 此去评论保存在application中，应用重新发布时会丢失。测试下而已
		 */
		  if(context.getAttribute("content")!=null)
	        {
			  
			  content =(String)context.getAttribute("content");
	        }
		  
		if(content!=null)
		content=content+text;
		else
			content=text;
		//将更改的评论写回
		context.setAttribute("content", content);
		// 转发
	request.getRequestDispatcher("/dis.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
