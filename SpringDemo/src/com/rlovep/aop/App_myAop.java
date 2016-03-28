package com.rlovep.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App_myAop {
       ApplicationContext ac= new ClassPathXmlApplicationContext("com/rlovep/aop/bean1.xml");
       @Test
       public void testAop(){
    	   IUserDao userDao=(IUserDao)ac.getBean("userDao");
    	   userDao.save();
       }
}
