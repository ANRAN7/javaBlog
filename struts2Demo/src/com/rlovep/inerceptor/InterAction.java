package com.rlovep.inerceptor;

import com.opensymphony.xwork2.ActionSupport;

public class InterAction extends ActionSupport{
	public InterAction() {
		System.out.println("Action构造");
	}
	public String execute(){
		System.out.println("执行execute");
		return SUCCESS;
	}

}
