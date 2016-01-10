package com.rlovep.compositekey;

import java.io.Serializable;
//复合主键类
public class Compositekeys implements Serializable{
	private String userName;
	private String address;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
