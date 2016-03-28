package com.rlovep.aop.anno;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private ApplicationContext ac=new ClassPathXmlApplicationContext("com/rlovep/aop/anno/bean.xml");
 // 目标对象有实现接口，spring会自动选择“JDK代理”
    @Test
    public void testAnnJdk(){
    	//此去必须用IuserDao接口转型
    	IUserDao userDao = (IUserDao)ac.getBean("userDao");
    	userDao.save();
    }
 // 目标对象没有实现接口， spring会用“cglib代理”
    @Test
    public void testAnnCglib(){
    	OrderDao orderDao=ac.getBean(OrderDao.class);
    	OrderDao orderDao1=ac.getBean(OrderDao.class);
    	System.out.println(orderDao);
    	System.out.println(orderDao1);
		try {
			orderDao.save();
		} catch (Exception e) {
			
		}
    }
}
