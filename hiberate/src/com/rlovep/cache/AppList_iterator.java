package com.rlovep.cache;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class AppList_iterator {
	private static SessionFactory sf=null;
	static{
		Configuration config=new Configuration().configure().addClass(User.class);
		//创建serviceRegistry  代替buildSessionFactory()方法
   		ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   		// 创建session的工厂对象
   		sf = config.buildSessionFactory(serviceRegistry);
	}
	//测试list
	@Test
	public void testList(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User"	);
		List<User> list = query.list();//直接从数据库中全部得到数据
		//只会执行一次查询，选择全部结果到list中；
		for(User u:list){
			System.out.println(u.getUserName());
			//u.setUserName("peace");
		}
		//执行第二次
		list = query.list();//会放入缓存，但是不会从缓存中获得数据；此处还是查询数据库
		//只会执行一次查询，选择全部结果到list中；
		for(User u:list){
			System.out.println(u.getUserName());
		}
		//测试list会放入缓存
		 Iterator<User> iterate = query.iterate();//向数据库中查询所有主键
	    while(iterate.hasNext())
	    	{ 	
	    	User user = iterate.next();
	    	System.out.println(user.getUserName());//从缓存中获得数据
	    	}
		tx.commit();
		session.close();		
	}
	//测试iterator
	@Test
	public void testIterator(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User"	);
	     Iterator<User> iterate = query.iterate();//向数据库中查询所有主键
	     int i=0;
	    while(iterate.hasNext())
	    	{ 	
	    	User user = iterate.next();
	    	System.out.println(user.getUserName());//会发送多次查询语句，如果缓存中有直接查询得到
	    	user.setUserName("peace"+i++);
	    	}
	    //执行第二次
	    iterate = query.iterate();//向数据库中查询所有主键，此处还是从数据库获得	 
	    while(iterate.hasNext())
	    	{ 	
	    	User user = iterate.next();
	    	System.out.println(user.getUserName());//从缓存中获得数据
	    	}
		tx.commit();
		session.close();		
	}
	
}
