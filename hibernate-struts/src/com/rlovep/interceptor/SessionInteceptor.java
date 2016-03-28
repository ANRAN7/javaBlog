package com.rlovep.interceptor;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.rlovep.entity.Dept;
import com.rlovep.utils.HibernateUtil;

public class SessionInteceptor extends AbstractInterceptor{

	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invokcation) throws Exception {
		//创建session给action和view用
		Session session = HibernateUtil.getsession();
		Transaction tx = session.beginTransaction();
		
		String result = invokcation.invoke();//此处返回时静态页面已经生成了不过服务器还没有将控制权给浏览器
		//此处可以用来关闭或提交用的事务
		ActionContext context = ActionContext.getContext();
		Dept dept = (Dept)context.getContextMap().get("dept");
		System.out.println(dept.getDeptName());
		//System.out.println(dept.getEmps());
		tx.commit();//这里不需要关闭session，会自动关闭
		return result;
	}

}
