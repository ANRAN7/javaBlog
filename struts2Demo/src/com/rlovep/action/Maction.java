package com.rlovep.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class Maction implements ModelDriven<User>{
	private User user=new User();

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String login(){
		System.out.println("name"+user.getUserName());
		System.out.println("pwd"+user.getPwd());
		//用ActionContext访问Servlet API
		//此方法返回一个ActionContext，该类有put和get方法，相当于request的setAttribute和getAttribute方法
		ActionContext context = ActionContext.getContext();
		context.put("name1", "peace_request");
		//当然也可以获得与request对象相对应的域对象的map
		Map<String, Object> request = context.getContextMap();
		request.put("name4", "peace_request1");
		//该方法获得与application对象相对应的域对象的map
		Map<String, Object> application = context.getApplication();
		application.put("name2", "peace_application");
		//该方法获得与session对象相对应的域对象的map
		Map<String, Object> session = context.getSession();
		session.put("name3", "peace_session");

		return "login";
	}

}
