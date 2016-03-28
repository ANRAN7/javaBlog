package com.rlovep.one2many;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.Session;
import org.junit.Test;


public class App_session {
	private static SessionFactory sf=null;
	static{
		Configuration config=new Configuration().configure().addClass(Dept.class).addClass(Employee.class);
		//创建serviceRegistry  代替buildSessionFactory()方法
   		ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   		// 创建session的工厂对象
   		sf = config.buildSessionFactory(serviceRegistry);
	}
	
	@Test
	public void testSession() throws Exception {
		//openSession:  创建Session, 每次都会创建一个新的session
		Session session1 = sf.openSession();
		Session session2 = sf.openSession();
		System.out.println(session1 == session2);
		session1.close();
		session2.close();
		
		//getCurrentSession 创建或者获取session
		// 线程的方式创建session  
		// 一定要配置：<property name="hibernate.current_session_context_class">thread</property>
		Session session3 = sf.getCurrentSession();// 创建session，绑定到线程
		Session session4 = sf.getCurrentSession();// 从当前访问线程获取session
		System.out.println(session3 == session4);
		
		// 关闭 【以线程方式创建的session，可以不用关闭； 线程结束session自动关闭】
		//session3.close();
		//session4.close(); 报错，因为同一个session已经关闭了！
	}
}
