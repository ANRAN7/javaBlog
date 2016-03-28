package com.rlovep.cache;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class AppCache {
	private static SessionFactory sf=null;
	static{
		Configuration config=new Configuration().configure().addClass(User.class);
		//创建serviceRegistry  代替buildSessionFactory()方法
   		ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   		// 创建session的工厂对象
   		sf = config.buildSessionFactory(serviceRegistry);
	}
	/**
	 * 
	* @Title: testStatus 
	* @Description: 测试对象的三种状态 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void testStatus(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		User user=new User();//new 出了的对象，临时状态
		user.setUserName("peace");//与数据库没有关联
		//保存到数据库：
		session.save(user);//调用save使对象持久化
		user.setUserName("peace5");//会改变到数据库
		
		//查询
		User user2 = (User) session.get(User.class, 3);//get到的是持久化状态
		user2.setUserName("peace4");//会改变到数据库
		tx.commit();
		session.close();
		//关掉后属于游离状态
		user.setUserName("peace6");//游离状态不会显示到数据库；条状到持久化状态只需要执行update，save之类的语句即可
	}
	/**
	 * 
	* @Title: testCache 
	* @Description: 测试一级缓存 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void testCache(){
		//创建两个session
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Session session2 = sf.openSession();
		Transaction tx2 = session2.beginTransaction();
		//获得一个user对象,会查询数据库
		User user=(User)session.get(User.class, 2);
		//使用同一个session再次获得一次,不会查询数据库，直接从一级缓存中获取，也叫session缓存
		user=(User)session.get(User.class, 2);		
		//使用不能的session获得同一对象，会查询数据库，说明不同的session不公用缓存
		User user2=(User)session2.get(User.class, 2);
		
		//将session1中的缓存user放入session2中，并执行更新
		session2.update(user);
		//修该对象
		user.setUserName("ppp");//会执行两条update语句
		tx2.commit();
		tx.commit();
		session2.close();
		session.close();
	}
	/**
	 * 
	* @Title: testFlush 
	* @Description: 测试刷新
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void testFlush(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		User user=null;
		user=(User)session.get(User.class, 2);
		user.setUserName("rong");
		//缓存数据与数据库同步session.createSQLQuery(sql).uniqueResult();
		session.flush();//注意flush只是与数据库缓存同步，没有提交到数据库。数据中是没有内容的；
		//使用flush刷新后，可以直接进行sql查询获得值；假如没有执行刷新，获得的将不是最新值；
		SQLQuery query = session.createSQLQuery("select * from User where userId=2");
		query.addEntity(User.class);
		List<User> list = query.list();
		System.out.println(list.get(0).getUserName());
		//tx.commit();  该方法会调用flush
		session.close();
	}
	@Test
	public void testClear(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		User user=null;
		user=(User)session.get(User.class, 2);
		//session.clear();//清空所有，会使user指向的对象变为游离状态
		session.evict(user);//清除指定对象，会使user指向的对象变为游离状态
		user=(User)session.get(User.class, 2);//会再次执行一次查询语句
		tx.commit();  //该方法会调用flush
		session.close();
	}
}
