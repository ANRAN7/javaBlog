package com.rlovep.entity;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	private ApplicationContext ac=new ClassPathXmlApplicationContext("config/bean-base.xml");
	@Test
	public void test(){
		//ac.getBean("deptDao");
		AppDao appDao = (AppDao)ac.getBean("appDao");
		appDao.testget();
	}
}
