package com.rlovep.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.rlovep.proxy._static.IUserDao;
import com.rlovep.proxy._static.UserDao;

public class App {
   public static void main(String[] args) {
	   //原始对象
	   UserDao userDao=new UserDao();
	   System.out.println(userDao.getClass());
	  /* //获得代理对象
	   IUserDao proxy=(IUserDao)new ProxyFactory(userDao).getProxy();
	   //
	   System.out.println(proxy.getClass());
	   //代理执行方法
	   proxy.save();*/
	   IUserDao proxy=(IUserDao)  Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), new InvocationHandler() {
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("执行事务");
			method.invoke(userDao, args);
			System.out.println("提交");
			return null;
		}
	});
	proxy.save();
}
}
