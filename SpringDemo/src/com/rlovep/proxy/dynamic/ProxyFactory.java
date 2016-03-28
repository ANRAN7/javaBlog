package com.rlovep.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
     private Object target;
     public ProxyFactory(Object target) {
		this.target=target;
	}
     //获得代理对象
     public Object getProxy()
     {
    	 //给目标生成代理对象象
    	 return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), 
    			    new InvocationHandler() {
						
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							System.out.println("开启事务");
							//执行目标方法
							Object returnValue = method.invoke(target, args);
							System.out.println("提交事务");
							return returnValue;
						}
					} );
     }
}
