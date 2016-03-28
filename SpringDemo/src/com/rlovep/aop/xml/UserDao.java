package com.rlovep.aop.xml;

public class UserDao implements IUserDao{

	
	@Override
	public void save() {
	
		System.out.println("userDao执行");
	
	}

}
