package com.rlovep.entity;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 * 用来创建数据库中的表
 */
public class AppDao {
	//工厂通过spring注入
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	//@Test
	public void test(){
		//sessionFactory=(SessionFactory)ac.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//保存管理员，并创建表
		Admin admin=new Admin();
		admin.setAdminName("admin");
		admin.setPwd("123456");
		session.save(admin);
		//保存部门和雇员，并创建表
		Dept dept1=new Dept();
		Dept dept2=new Dept();
		Employee employee=new Employee();
		Employee employee2=new Employee();
		Employee employee3=new Employee();
		Employee employee4=new Employee();
		dept1.setName("ptool1");
		dept2.setName("ptool2");
		employee.setEmpName("peace1");
		employee.setSalary(6000);
		employee.setDept(dept1);
		employee2.setEmpName("peace2");
		employee2.setSalary(7000);
		employee2.setDept(dept1);
		employee3.setEmpName("peace3");
		employee3.setSalary(86000);
		employee3.setDept(dept2);
		employee4.setEmpName("peace4");
		employee4.setSalary(9000);
		employee4.setDept(dept2);
		//持久化
		session.save(dept1);
		session.save(dept2);
		session.save(employee);
		session.save(employee2);
		session.save(employee3);
		session.save(employee4);
		tx.commit();
		session.close();
	}
	public void testget(){
		//sessionFactory=(SessionFactory)ac.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Employee");
		List list = query.list();
		System.out.println(list.get(0));
		tx.commit();
		session.close();
	}
}
