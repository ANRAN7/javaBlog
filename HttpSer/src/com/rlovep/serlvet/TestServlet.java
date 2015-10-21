package com.rlovep.serlvet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet(name="TestServlet",loadOnStartup=1,urlPatterns={"/TestServlet"})
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     int i=0;  
    /**
     * 1.构造方法，只被调用一次
     */
    public TestServlet() {
        super();
        System.out.println("构造方法>>>>");
    }
    /**
     * 2.init初始化方法，在构造方法后只被调用一次
     * 有参数的init方法会调用init方法；；一般覆盖无参数的init方法；
     */
     @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	System.out.println("inti>>>>");
    }
    /**
     * 3.service方法，发生请求和响应时调用的方法,次数不限
     * 一般是重写doget和dopost，此去只是方便演示
     */
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service>>>" +i++);
	}
  /**
   * 4.destory方法，只有在停止服务器和重新部署web应用时调用
   */
	@Override
	public void destroy() {
		System.out.println("destory");
		super.destroy();
	}
}
