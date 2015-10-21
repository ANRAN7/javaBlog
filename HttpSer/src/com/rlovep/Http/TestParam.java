package com.rlovep.Http;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestParam
 */
@WebServlet("/TestParam")
public class TestParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestParam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 设置参数查询的编码
		 * 该方法只能对请求实体内容的数据编码起作用。POST提交的数据在实体内容中，所以该方法对POST方法有效！
		 * GET方法的参数放在URI后面，所以对GET方式无效！！！
		 */
		request.setCharacterEncoding("utf-8");
		//获得所有的方式
		
		System.out.println("提交方式："+request.getMethod());
		//获得输入参数
		String name=request.getParameter("name");
		String pass=request.getParameter("password");
		System.out.println("name:"+name+",pass:"+pass);
		/*此去为如果get方式提交出现乱码，使用；
		 * if("GET".equals(request.getMethod())){
		password = new String(password.getBytes("iso-8859-1"),"utf-8");
	}*/
		System.out.println(">>>>>>>>>>>>>>>>>");
		//获得所有输入参数的名字
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements())
		{
			String names=params.nextElement();
			//如果是复选框，使用getParameterValues(names);方法
			if("hobit".equals(names)){
			   System.out.println(names+":");
			   String[] hobits = request.getParameterValues(names);
			   for(String s:hobits)
				   System.out.print(s+",");
				System.out.println();
			}
		
			else{
				System.out.println(names+":"+request.getParameter(names));
			}
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
