package com.rlovep.extend;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

/**
 * 
* @ClassName: App_three
* @Description: 每个类都带有一张表,父类页对应一张表
* @author peace w_peace@163.com 
* @date 11 Jan 2016 5:17:44 pm
*
 */
public class App_three {
	private static SessionFactory sf=null;
	static{
		Configuration config=new Configuration().configure().addResource("com/rlovep/extend/Animal2.hbm.xml");
		//创建serviceRegistry  代替buildSessionFactory()方法
   		ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   		// 创建session的工厂对象
   		sf = config.buildSessionFactory(serviceRegistry);
	}
	@Test
	public void testSave(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//直接存储
	    Cat cat=new Cat();
	    cat.setName("黑猫");
	    cat.setCatchMouse("抓老鼠");

		Monkey m = new Monkey();
		m.setName("猴子");
		m.setEatBanana("吃10个香蕉");
	    //保存
	    session.save(cat);
	    session.save(m);
		tx.commit();  //该方法会调用flush
		session.close();
	}
	@Test
	public void testGet(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//直接存储
	    Cat cat=null;
		Monkey m = null;
		//获得Animal
	    Animal animal = (Animal)session.get(Animal.class, 1);
	    System.out.println(animal.getName());
	    //可以向下转型为cat
	    if(animal instanceof Cat)
	    	System.out.println(((Cat)animal).getCatchMouse());
	    //直接获得子类
	    Query query = session.createQuery("from com.rlovep.extend.Cat");
	     List<Cat> list = query.list();
	     System.out.println(list.get(0).getName());
	    //保存
	    session.save(cat);
	    session.save(m);
		tx.commit();  //该方法会调用flush
		session.close();
	}
}
