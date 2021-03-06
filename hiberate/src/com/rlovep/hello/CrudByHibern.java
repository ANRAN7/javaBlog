package com.rlovep.hello;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
/**
 * 
* @ClassName: CrudByHibern
* @Description: 增删改查
* @author peace w_peace@163.com 
* @date 7 Jan 2016 10:49:25 am
*
 */
public class CrudByHibern {
	
	private static SessionFactory sf=null;
	static{
		Configuration config=new Configuration();
		// 默认加载src/hibenrate.cfg.xml文件
		config.configure();  
		//创建serviceRegistry  代替buildSessionFactory()方法
   		ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   		// 创建session的工厂对象
   		sf = config.buildSessionFactory(serviceRegistry);
	}
	@Test
	public void testSave(){
		//对象
		Employee emp=new Employee();
		emp.setEmpName("peace2");
		emp.setWorkDate(new Date());
		//获得session
		Session session = sf.openSession();
		//开启事务
		Transaction tx = session.beginTransaction();
		//执行操作
		session.save(emp);
		
		//提交事务和关闭session
		tx.commit();
		session.close();
	}
	@Test
	public void testdelete() {
		// 对象
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("peace1");
		emp.setWorkDate(new Date());
		// 获得session
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// 执行操作
		session.delete(emp);

		// 提交事务和关闭session
		tx.commit();
		session.close();
	}

	@Test
	public void testUpdate() {
		// 对象
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("peace1");
		emp.setWorkDate(new Date());
		// 获得session
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// 执行操作
		session.saveOrUpdate(emp);

		// 提交事务和关闭session
		tx.commit();
		session.close();
	}
	//HQL查询  【适合有数据库基础的】 区分大小写 比喻将Employee变为employee就会报错
	@Test
	public void testQuery() {
		// 获得session
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// 执行操作
	    Query query = session.createQuery("from Employee where empId=1 or empId=2");
	    List<Employee>list=query.list();
	    System.out.println(list);
		// 提交事务和关闭session
		tx.commit();
		session.close();
	}
	//QBC查询  , query by criteria  完全面向对象的查询
	@Test
	public void testQBC() {
		// 获得session
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// 执行操作
	   //条件
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("empId", 1));
		//查询
		List<Employee> list = criteria.list();
	    System.out.println(list);
		// 提交事务和关闭session
		tx.commit();
		session.close();
	}
	@Test
	public void testSqlQuery() {
		// 获得session
		Session session = sf.openSession();
		// 开启事务
		Transaction tx = session.beginTransaction();
		// 执行操作
	    Query query = session.createSQLQuery("select * from employee where empId=1 or empId=2").addEntity(Employee.class);
	    List<Employee>list=query.list();
	    System.out.println(list);
		// 提交事务和关闭session
		tx.commit();
		session.close();
	}
	// 自动建表
		@Test
		public void testCreate() throws Exception {
			// 创建配置管理类对象
			Configuration config = new Configuration();
			// 加载主配置文件
			config.configure();
			
			// 创建工具类对象
			SchemaExport export = new SchemaExport(config);
			// 建表
			// 第一个参数： 是否在控制台打印建表语句
			// 第二个参数： 是否执行脚本
			export.create(true, true);
		}
}
