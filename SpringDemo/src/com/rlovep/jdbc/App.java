package com.rlovep.jdbc;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private ApplicationContext ac=new ClassPathXmlApplicationContext("com/rlovep/jdbc/bean.xml");
    @Test
    public void testJdbc(){
    	DeptDao dao=(DeptDao)ac.getBean("deptDao");
    	dao.save();
    	Dept dept = dao.findById(1);
    	System.out.println(dept.getDeptName());
    	List<Dept> list = dao.getAll();
    	System.out.println(list);
    }
}
