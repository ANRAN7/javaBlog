package com.rlovep.type;

import java.util.Date;

/**
 * 
* @ClassName: UserAction
* @Description: Struts核心业务： 请求数据自动封装以及类型转换
* @author peace w_peace@163.com 
* @date 26 Dec 2015 3:44:01 pm
*
 */
public class UserAction {
	


	private String name;  
	private String pwd;
	private int age;
	private Date birth;
	public void setName(String name) {
		this.name = name;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getName() {
		return name;
	}
	public String getPwd() {
		return pwd;
	}
	public int getAge() {
		return age;
	}
	public Date getBirth() {
		return birth;
	}
	// 自动转换
	public String execute() {
		System.out.println(name);
		System.out.println(pwd);
		System.out.println(age);
		System.out.println(birth);
		return "success";
	}
}
