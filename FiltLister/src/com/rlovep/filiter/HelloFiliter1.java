package com.rlovep.filiter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloFiliter1 implements Filter{

	@Override
	public void destroy() {
	System.out.println("毁坏第一个");
	System.out.println("毁坏第一个");
	
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain)
			throws IOException, ServletException {
		    //第二个过滤器
		   System.out.println("执行第一个过滤器");
		   chain.doFilter(req, rsp);
		   System.out.println("执行完第一个");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("初始化第一个");
		System.out.println("初始化第一个");
	}

}
