package com.rlovep.aop;

import org.springframework.stereotype.Component;

@Component
public class UserDao1 implements IUserDao {

	@Override
	public void save() {
		System.out.println("实际的保存方法执行");
		
	}

}
