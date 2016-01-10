package com.rlovep.inerceptor.demo.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserCheckIner extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取ActionContext对象
		ActionContext context = invocation.getInvocationContext();
		//获得Action的代理，并获得方法
		ActionProxy actionProxy = invocation.getProxy();
		String method = actionProxy.getMethod();
		 // 判断是否登陆过
		if(!method.equals("login"))
		{
			 // 先获取当前登陆的用户
			Object admin = context.getSession().get("userinfo");
			if(admin==null)
				// 没有登陆
				return "input";
			 // 当前用户有登陆
			return invocation.invoke();
		}
		//否则就是正在登陆
		return invocation.invoke();
	}

}
