package com.rlovep.tx.ann;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
      private ApplicationContext ac=new ClassPathXmlApplicationContext("com/rlovep/tx/ann/bean.xml");
      @Test
      public void testTx(){
    	  DeptService service=(DeptService)ac.getBean("deptService");
    	  Dept dept=new Dept();
    	  dept.setDeptName("peace2");
    	  service.save(dept);
      }
}
