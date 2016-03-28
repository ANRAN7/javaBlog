package com.rlovep.aop;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class UserDao implements IUserDao{
    //重复执行代码的类
	@Resource
	private Aop aop;
	
	
	@Override
	public void save() {
		aop.begin();
		System.out.println("userDao执行");
		aop.commite();
	}

}
