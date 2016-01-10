package com.rlovep.inerceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class HelloInreceptor implements Interceptor{
    public HelloInreceptor() {
    	System.out.println("构造拦截器");
	}
	@Override
	public void destroy() {
		System.out.println("拦截器销毁");
	}

	@Override
	public void init() {
		System.out.println("执行init方法");
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("执行Action之前");
		// 调用下一个拦截器或执行Action  (相当于chain.doFilter(..)
				// 获取的是： execute方法的返回值
		String result = invocation.invoke();
		System.out.println("拦截器业务处理结束__" +result);
		
		return result;
		
		
	}

}
