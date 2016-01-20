package com.rlovep.action.inter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class AdminInterceptor extends AbstractInterceptor{

	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//得到当前执行的方法
		String method = invocation.getProxy().getMethod();
		//判断：当不为登陆方法和list方法时
		if(!"login".equals(method)&&!"list".equals(method)){
			Object obj= ActionContext.getContext().getSession().get("adminInfo");
			if(obj==null){
				//没有登陆
				return "login";
			}else{
				//放行
				return invocation.invoke();
			}
		}
		//放行
		return invocation.invoke();
	}

}
