package com.rlovep.beanutil;

import java.util.Date;

/**
 * 1. javabean
 * @author Jie.Yuan
 *
 */
public class Admin {

	private int id;
	private String userName;
	private String pwd;
	private int age;
	private Date birth;
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Admin [age=" + age + ", birth=" + birth + ", id=" + id
				+ ", pwd=" + pwd + ", userName=" + userName + "]";
	}
	
	
}
