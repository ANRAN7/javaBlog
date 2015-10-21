package com.rlovep.Http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestRespose
 */
@WebServlet("/TestRespose")
public class TestRespose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestRespose() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 更改响应行，主要是改状态码
		 * 
		 */
		//response.setStatus(404);//错误代码,没有反应
		//response.sendError(404);// 发送404的状态码+404的错误页面
		/**
		 * 修改响应头：
		 * 
		 */
		//修改服务器类型
		//response.setHeader("server", "JBoss");
		/**
		 * 修改实体内容
		 */
		//浏览器能直接看到的内容就是实体内容
		//response.getWriter().println("hello peace");//字符内容，常用
		//response.getOutputStream().write("hello world".getBytes());//字节内容。不能两个同时使用
		/**
		 * 测试重定向：与转发不同
		 * 使用请求重定向：发送一个302状态吗+location的响应
		 * 
		 */
			/*response.setStatus(302);//设置状态码
			response.setHeader("location", "/HttpSer/adv.html");//设置重定向页面
*/		//简单写法
	//	response.sendRedirect("/HttpSer/adv.html");
		/**
		 * 定时刷新
		 * 原理：浏览器解析refresh头，得到头之后重新请求当前资源
		 *
		 */
		//response.setHeader("refresh", "1");//每隔1秒刷新一次
		//隔5秒后转到另外的资源
		//response.setHeader("refresh", "5;url=/HttpSer/adv.html");
		/**
		 * 设置响应实体内容编码
		 */
		//response.setCharacterEncoding("utf-8");
		
		/**
		 * 1. 服务器发送给浏览器的数据类型和内容编码
		 */
		//response.setHeader("content-type", "text/html");//设置内容为html
		//response.setContentType("text/html;charset=utf-8");//和上面代码等价。推荐使用此方法
		//response.setContentType("text/xml");//设置内容为xml
		response.setContentType("image/png");//设置内容为图片
		//设置输出内容
		//response.getOutputStream().write("<html><head><title>this is tilte</title></head><body>中国</body></html>".getBytes("utf-8"));
			ServletContext servletContext = getServletContext();
			String realPath = servletContext.getRealPath("/image/1.png");
		    File file = new File(realPath);//WebContent
		    InputStream resourceAsStream = servletContext.getResourceAsStream("/image/1.png");
		/**
		 * 设置以下载方式打开文件
		 */
		//response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
		/**
		 * 发送图片
		 */
		/*FileInputStream in = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int len = 0;
		
		//把图片内容写出到浏览器
		while( (len=in.read(buf))!=-1 ){
			response.getOutputStream().write(buf, 0, len);
		}*/
		
		byte[] buf1 = new byte[1024];
		int len1 = 0;
		
		//把图片内容写出到浏览器
		while( (len1=resourceAsStream.read(buf1))!=-1 ){
			response.getOutputStream().write(buf1, 0, len1);
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
