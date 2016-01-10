package com.rlovep.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(urlPatterns="/*")
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain) throws IOException, ServletException {
		//向下转型
		final HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)rsp;
		//处理公用业务对post方式有效
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;utf-8");
		/*
		 * 出现GET中文乱码，是因为在request.getParameter方法内部没有进行提交方式判断并处理。
		 * String name = request.getParameter("userName");
		 * 
		 * 解决：对指定接口的某一个方法进行功能扩展，可以使用代理!
		 *      对request对象(目标对象)，创建代理对象！
		 */
		//给request对象增加动态代理，使得当get方式提交时一样可以正常显示
		HttpServletRequest proxy=(HttpServletRequest)Proxy.newProxyInstance(request.getClass().getClassLoader(), 
				                                            new Class[]{HttpServletRequest.class},//接口类型
				                                            new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Object result=null;//执行反射方法调用时的返回值
				String methodname=method.getName();//获得方法名
				if("getParameter".equals(methodname)){
					String value=request.getParameter(args[0].toString());
					// 判断如果是GET提交，需要对数据进行处理  (POST提交已经处理过了)
					if("get".equalsIgnoreCase(request.getMethod())){
						if(value!=null && !"".equals(value.trim()))
						{
							// 处理GET中文
							value=new String(value.getBytes("ISO8859-1"),"UTF-8");
						}
					}
					return value;
				}
				else
				{
					//对对象方法掉用。
					result=method.invoke(request, args);
				}
				
				return result;
			}
		});
		//将代理作为request传入
		chain.doFilter(proxy, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
