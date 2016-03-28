package com.rlovep.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.stereotype.Component;



public class ProxyFactory {
	// 目标对象
		private static Object target;
		private static Aop aop;
        private static int k;
		// 生成代理对象的方法，这里相当于代理对象的构造器
		public static Object getProxyInstance(Object target_,Aop aop_,int k_){
			
			target = target_;
			aop = aop_;
			k=k_;
			return Proxy.newProxyInstance(
					target.getClass().getClassLoader(), 
					target.getClass().getInterfaces(), 
					new InvocationHandler() {
						
						@Override
						public Object invoke(Object proxy, Method method, Object[] args)
								throws Throwable {
							aop.begin();// 执行重复代码
							
							// 执行目标对象的方法
							Object returnValue = method.invoke(target, args);
							System.out.println(k);
							aop.commite(); // 执行重复代码
							return returnValue;
						}
					});
		}
}
