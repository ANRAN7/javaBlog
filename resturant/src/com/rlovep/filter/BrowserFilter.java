package com.rlovep.filter;

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
 * Servlet Filter implementation class BrowserFilter
 */
/*@WebFilter(value="/*")*/
public class BrowserFilter implements Filter {

  
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		String userAgent = request.getHeader("user-gent");
		if(userAgent.contains("Firefox")){
			chain.doFilter(request, response);
		}else if(userAgent.contains("Chrome")){
			chain.doFilter(request, response);
		}else if(userAgent.contains(".NET")){
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
