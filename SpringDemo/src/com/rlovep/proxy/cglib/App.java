package com.rlovep.proxy.cglib;

import com.rlovep.proxy._static.IUserDao;
import com.rlovep.proxy._static.UserDao;

public class App {
	  public static void main(String[] args) {
		   //原始对象
		   UserDao userDao=new UserDao();
		   System.out.println(userDao.getClass());
		   //获得代理对象
		   IUserDao proxy=(IUserDao)new ProxyFactory(userDao).getProxyInstance();
		   //
		   System.out.println(proxy.getClass());
		   //代理执行方法
		   proxy.save();
		
	}
}
