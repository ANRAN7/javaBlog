package com.rlovep.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rlovep.entity.Admin;
import com.rlovep.entity.Employee;
import com.rlovep.service.IAdminService;
import com.rlovep.service.IDeptService;
import com.rlovep.service.IEmployeeService;

public class App {
	//加载spring的配置文件
   private ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
   //测试Admin的操作
   @Test
   public void testAdmin(){
	   //获得bean
	 IAdminService adminService=(IAdminService)ac.getBean("adminService");
	 Admin admin=new Admin();
	 admin.setAdminName("admin");
	 admin.setPwd("123456");
	System.out.println( adminService.login(admin));
   }
   //测试Dept的操作
   @Test
   public void testDept(){
	 IDeptService service=( IDeptService)ac.getBean("deptService");
	System.out.println( service.findById(1));
   }
   //测试Employee的操作
   @Test
   public void testEmployee(){
	 IEmployeeService service=( IEmployeeService)ac.getBean("employeeService");
	 List<Employee> list = service.getAll();
	System.out.println( service.findById(9));
   }
}
