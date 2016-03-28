package com.rlovep.one2one;

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
		Configuration config=new Configuration().configure().addClass(UserI.class).addClass(IdCard.class);
		//创建serviceRegistry  代替buildSessionFactory()方法
   		ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   		// 创建session的工厂对象
   		sf = config.buildSessionFactory(serviceRegistry);
	}
	@Test
	public void testSave(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		UserI user=new UserI();
		user.setUserName("peace");
		IdCard idCard=new IdCard();
		idCard.setCardNum("4306261991**");
		idCard.setPlace("五山");
		//关系建立
		idCard.setUser(user);//这里建立关系，必须这样建立。user.setIdCard(idCard)不会保存idcard		
		//保存
		session.save(idCard);
		tx.commit();
		session.close();
	}
	@Test
	public void testGet(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		UserI user=null;
		IdCard idCard=null;
		//不能获得User中的Idcard
		user= (UserI)session.get(UserI.class, 1);	
		System.out.println(user.getIdCard().getCardNum());
		System.out.println("-----------");
		//idCard=(IdCard)session.get(IdCard.class, "4306261991**");//外键用这个查询
		idCard=(IdCard)session.get(IdCard.class, 1);
		System.out.println(idCard.getUser().getUserName());
		
		tx.commit();
		session.close();
	}
}
