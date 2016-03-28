package com.rlovep.tx.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
      private ApplicationContext ac=new ClassPathXmlApplicationContext("com/rlovep/tx/xml/bean.xml");
      @Test
      public void testTx(){
    	  DeptService service=(DeptService)ac.getBean("deptService");
    	  Dept dept=new Dept();
    	  dept.setDeptName("peace1");
    	  service.save(dept);
      }
}
