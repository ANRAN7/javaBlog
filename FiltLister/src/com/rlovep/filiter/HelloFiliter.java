package com.rlovep.filiter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 过滤器必须实现Filter接口
 * 
 */
/**
 * 
* @ClassName: HelloFiliter
* @Description: 监听器实现
* @author peace w_peace@163.com 
* @date 18 Nov 2015 3:31:39 pm
*
 */
/*
 * @WebFilter(urlPatterns="/*")
 * urlPatterns="/*"过滤所有路径
 * 参考servlet
 * dispatcherTypes=DispatcherType.FORWARD触发过滤器的时机为转发。该值如果不写，时机为REQUEST
 * initParams={@WebInitParam(name="encoding",value="utf-8"),
   					    @WebInitParam(name="driver",value="msql"配置参数。
 */
@WebFilter(urlPatterns="/*",dispatcherTypes=DispatcherType.FORWARD,
   			initParams={@WebInitParam(name="encoding",value="utf-8"),
   					    @WebInitParam(name="driver",value="msql")}
		)
public class HelloFiliter implements Filter {

    /**
     * Default constructor. 
     */
    public HelloFiliter() {
    	System.out.println("filter构造");
    }
    public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("filter初始化");
		//通过fConfig获得参数
		FilterConfig filterConfig=fConfig;
		String encoding=filterConfig.getInitParameter("encoding");
		//或者通过迭代获得
		Enumeration<String> names = filterConfig.getInitParameterNames();
		while (names.hasMoreElements()) {
			String string = (String) names.nextElement();
			String value=filterConfig.getInitParameter(string);
			System.out.println(string+"="+value);
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("对请求执行过滤");
		// 放行 (去到service)
				// 如果有下一个过滤器，进入下一个过滤器，否则就执行访问service
		chain.doFilter(request, response);
		System.out.println("对响应进行过滤");
	}
	public void destroy() {
	    System.out.println("filter消灭");
	    
	}

}
