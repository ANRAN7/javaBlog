package com.rlovep.hello;

public class HelloAction {
    //定义属性用于获得请求参数
	private String name;
	private String pass;
	public void setName(String name) {
		this.name = name;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	//定义action类的execute方法
	public String execute()throws Exception{
		if("peace".equals(name)&&"123456".equals(pass)){
			return "success";
			
		}
		else{
			return "error";
		}
	}
}
