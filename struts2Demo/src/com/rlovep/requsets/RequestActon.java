package com.rlovep.requsets;

public class RequestActon {
	private String name;
	private int pwd;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPwd() {
		return pwd;
	}
	public void setPwd(int pwd) {
		this.pwd = pwd;
	}
	
	//一个提交请求方法
	public String register(){
		System.out.println(name+"=" +pwd);
		return "register";
	}
	//另外一个提交请求方法
		public String execute(){
			System.out.println(name+"=" +pwd);
			return "success";
		}

}
