package com.rlovep.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App_Jdk {
    ApplicationContext ac= new ClassPathXmlApplicationContext("com/rlovep/aop/bean2.xml");
    @Test
    public void testAop(){
 	   IUserDao userDao=(IUserDao)ac.getBean("userDao_Factory");
 	   userDao.save();
    }
}
