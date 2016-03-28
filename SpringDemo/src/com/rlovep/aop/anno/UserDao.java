package com.rlovep.aop.anno;

import org.springframework.stereotype.Component;

@Component
public class UserDao implements IUserDao{

	
	@Override
	public void save() {
	
		System.out.println("userDao执行");
	
	}

}
