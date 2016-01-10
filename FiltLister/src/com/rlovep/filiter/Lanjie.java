package com.rlovep.filiter;

import java.io.IOException;
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
 * Servlet Filter implementation class Lanjie
 */
@WebFilter(urlPatterns="/*")
public class Lanjie implements Filter {

    /**
     * Default constructor. 
     */
    public Lanjie() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 *对指定的uri进行拦截
	 *拦截/lanjie.jsp
	 */
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain) throws IOException, ServletException {
		// place your code here
       HttpServletRequest request=(HttpServletRequest) req;
       HttpServletResponse response=(HttpServletResponse)rsp;
       String uri=request.getRequestURI();
       String s=uri.substring(uri.lastIndexOf("/")+1, uri.length());
       if(!"lanjie.jsp".equals(s))
		chain.doFilter(request, response);
       else
    	   request.getRequestDispatcher("/404.html").forward(request, response);;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
