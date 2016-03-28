package com.rlovep.proxy._static;

public class UserDao implements IUserDao{

	@Override
	public void save() {
		System.out.println("userDao执行");
		
	}

}
