package com.rlovep.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import com.rlovep.utils.HibernateUtil;

public class App_save {
	
	//测试保存：一对多的保存
	//是否设置inverse 对保存的影响
	//  为false时能正常保存，多输出两条update语句；
   //  为true时能保存，没有update语句，但是员工表，不能关联到部门表
	@Test
	public void testSave1(){
		Session session = HibernateUtil.getsession();
		Transaction tx = session.beginTransaction();
		//创建对象
		Employee employee=new Employee();
		Employee employee2=new Employee();
		employee.setEmpName("peace3");
		employee.setSalary(300);
		employee2.setEmpName("peace4");
		employee2.setSalary(499);
		//关系set创建
		Set<Employee>emps=new HashSet<>();
		emps.add(employee);
		emps.add(employee2);
		//创建dept
		Dept dept=new Dept();
		dept.setDeptName("hibetnate2项目");
		dept.setEmps(emps);
		//持久化
		session.save(dept);
		tx.commit();
		
	}
}
