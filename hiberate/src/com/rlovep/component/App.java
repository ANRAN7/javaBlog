package com.rlovep.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;


//测试一对一
public class App {
	private static SessionFactory sf=null;
	static{
		Configuration config=new Configuration().configure().addClass(Car.class);
		//创建serviceRegistry  代替buildSessionFactory()方法
   		ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   		// 创建session的工厂对象
   		sf = config.buildSessionFactory(serviceRegistry);
	}
	@Test
	public void testSave(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		// 轮子
		Wheel wheel = new Wheel();
		wheel.setSize(38);
		wheel.setCount(4);
		// 汽车
		Car car = new Car();
		car.setName("BMW");
		car.setWheel(wheel);

		// 保存
		session.save(car);
		tx.commit();
		session.close();
	}
	@Test
	public void testGet(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Car car=(Car)session.get(Car.class, 1);
		System.out.println(car.getName());
		System.out.println("-----");
		System.out.println(car.getWheel().getSize()+":"+car.getWheel().getCount());
		
		tx.commit();
		session.close();
	}
}
