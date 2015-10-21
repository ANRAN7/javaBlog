package com.rlovep.Http;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestRequst
 */
@WebServlet("/TestRequst")
public class TestRequst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestRequst() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		test(request);
		//testAdd(request, response);
		
	}

	private void testAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String refer=request.getHeader("referer");
		System.out.println("referer="+refer);
		//判断是否为非法链接
		if(refer==null || !refer.contains("/HttpSer/adv.html"))
		{
			response.getWriter().write("当前是非法链接，请回到首页。<a href='/HttpSer/adv.html'>首页</a>");
		}
		else{
			response.getWriter().write("资源正在下载");
		}
	}

	private void test(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
		/**
		 * 设置参数查询的编码
		 * 该方法只能对请求实体内容的数据编码起作用。POST提交的数据在实体内容中，所以该方法对POST方法有效！
		 * GET方法的参数放在URI后面，所以对GET方式无效！！！
		 */
		request.setCharacterEncoding("utf-8");
		/**
		 * 1.1请求行的获得
		 */
		System.out.println("请求方式："+request.getMethod());
		System.out.println("请求URI："+request.getRequestURI());
		System.out.println("请求url："+request.getRequestURL());
		System.out.println("获得协议："+request.getProtocol());
		/**
		 * 1.2请求头的获得
		 */
		//通过键获得请求头的内容
		System.out.println("获得host："+request.getHeader("Host"));
		System.out.println("获得浏览器的User-Agent："+request.getHeader("User-Agent"));
		//通过迭代器迭代,获得键 在取键值
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String key=headerNames.nextElement();
			System.out.println(key+":"+request.getHeader(key));
		}
		/**
		 * 得到请求实体内容
		 * 比如：实体为name=peace&pass=1234
		 */
		ServletInputStream in = request.getInputStream();
		byte[] buf=new byte[1024];
		int len=0;
		while((len=in.read(buf))!=-1){
			String str=new String(buf,0,len);
			System.out.println(str);
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
