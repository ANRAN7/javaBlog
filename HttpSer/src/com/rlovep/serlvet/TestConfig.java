package com.rlovep.serlvet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TestConfig
 */
/*@WebServlet(urlPatterns={"/TestConfig"},
        initParams={@WebInitParam(name="driver",value="com.mysql*")
                    ,@WebInitParam(name="url",value="jdbc*"),
                    @WebInitParam(name="user",value="root"),
                    @WebInitParam(name="pass",value="123456")})*/
public class TestConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*ServletConfig servletConfig = getServletConfig();
        //得到servlet的名字
		System.out.println(servletConfig.getServletName());
		//根据参数名获取参数值
		String name=servletConfig.getInitParameter("user");
		System.out.println(name+">>>>>");
		//获取所有参数名称
		Enumeration<String> names = servletConfig.getInitParameterNames();
		while(names.hasMoreElements()){
			String s=names.nextElement();
			System.out.println(s+"="+servletConfig.getInitParameter(s));
		}*/
		ServletContext servletContext = getServletContext();
		//得到当前web应用路径
		System.out.println("路径："+servletContext.getContextPath());
		//根据参数名获得参数值
		System.out.println("AAA="+servletContext.getInitParameter("AAA"));
		//获取所有参数名称
		Enumeration<String> names = servletContext.getInitParameterNames();
		while(names.hasMoreElements()){
			String s=names.nextElement();
			System.out.println(s+":"+servletContext.getInitParameter(s));
		}
		//设置域对象，整个web应用有效
		servletContext.setAttribute("name","peace");
		servletContext.setAttribute("age", "23");
		//获得域对象
		System.out.println("name"+servletContext.getAttribute("name"));
		System.out.println("age"+servletContext.getAttribute("age"));
		//删除域对象
		servletContext.removeAttribute("age");
		System.out.println("age"+servletContext.getAttribute("age"));
		//
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
