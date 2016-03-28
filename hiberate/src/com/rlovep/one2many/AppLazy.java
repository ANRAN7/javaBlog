package com.rlovep.one2many;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

//测试懒加载
public class AppLazy {
	private static SessionFactory sf=null;
	static{
		Configuration config=new Configuration().configure().addClass(Dept.class).addClass(Employee.class);
		//创建serviceRegistry  代替buildSessionFactory()方法
   		ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   		// 创建session的工厂对象
   		sf = config.buildSessionFactory(serviceRegistry);
	}
	//测试懒加载对get的影响
	@Test
	public void test_get(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//get及时查询，不管使用dept与否都会执行查询语句与懒加载无关
		Dept dept=(Dept)session.get(Dept.class, 1);
		/*System.out.println(dept.getDname());
		System.out.println("------");
		System.out.println(dept.getEmps().isEmpty());//但是集合，set，list，map等是属于懒加载的，当不使用时getEmps是，此处输出true；
*/		tx.commit();
		session.close();
		//session关闭也可以获得数据
		System.out.println(dept.getDname());
	}
	//测试懒加载对load的影响，
		@Test
		public void test_load(){
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			// load，默认懒加载， 及在使用数据的时候，才向数据库发送查询的sql语句！
			Dept dept=(Dept)session.load(Dept.class, 1);
			/*//解决懒加载报错办法：
			 //1.先使用一下数据
			dept.getDname();
			//2.强迫代理对象初始化
			Hibernate.initialize(dept);*/
			
			tx.commit();
			session.close();
			//如果关闭session使用dept，会报错。
			System.out.println(dept.getDname());
		}
}
