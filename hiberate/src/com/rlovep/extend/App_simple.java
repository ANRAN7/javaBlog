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
* @ClassName: App_simple
* @Description: 简单继承测试
* @author peace w_peace@163.com 
* @date 11 Jan 2016 5:00:43 pm
*
 */
public class App_simple {
	private static SessionFactory sf=null;
	static{
		Configuration config=new Configuration().configure().addClass(Cat.class);
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
	    session.save(cat);
		tx.commit();  //该方法会调用flush
		session.close();
	}
	
	@Test
	public void testget(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
	     Cat cat=null;
	     //通过id查询
	     cat = (Cat)session.get(Cat.class, 1);
	     System.out.println(cat.getCatchMouse());
	     //通过sql查询
	     Query query = session.createQuery("from Cat");
	     List<Cat> list = query.list();
	     System.out.println(list.get(0).getName());
	  // 获取时候注意：当写hql查询的使用，通过父类查询必须写上类的全名
	     Query query2 = session.createQuery("from com.rlovep.extend.Animal");
	     List<Animal> list2=query2.list();
	     System.out.println(list2.get(1).getName());
		tx.commit();  //该方法会调用flush
		session.close();
	}
}
