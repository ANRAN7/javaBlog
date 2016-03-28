package com.rlovep.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class ProxyFactory implements MethodInterceptor{
    //需要代理的对象
	private Object target;
	public ProxyFactory(Object target) {
		this.target=target;
	}
	//给目标对象创建代理对象
	public Object getProxyInstance() {
		//工具类
		Enhancer en=new Enhancer();
		//设置父类
		en.setSuperclass(target.getClass());
		//设置回调函数
		en.setCallback(this);
		//创建代理对象
		return en.create();
	}
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("开始事务----");
		//执行目标方法
		Object returnValue = method.invoke(target, args);
		System.out.println("提交事务");
		return returnValue;
	}

}
