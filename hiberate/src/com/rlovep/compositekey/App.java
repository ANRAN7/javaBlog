package com.rlovep.compositekey;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.rlovep.Utils.HibernateUtil;




/**
 * 
* @ClassName: App
* @Description: 测试复合主键
* @author peace w_peace@163.com 
* @date 7 Jan 2016 1:03:42 pm
*
 */
public class App {
	/*// 创建sf对象
			sf = new Configuration()
				.configure()
				.addClass(User.class)  //（测试） 会自动加载映射文件：Employee.hbm.xml
				.buildSessionFactory();*/
	  @Test
       public void testSave(){
    	   Session session = HibernateUtil.getsession();
    	   Transaction tx = session.beginTransaction();
    	   //对象创建
    	   Compositekeys keys=new Compositekeys();
    	   keys.setAddress("广东");
    	   keys.setUserName("peace");
    	   User user=new User();
    	   user.setKeys(keys);
    	   user.setAge(23);
    	   //添加
    	   session.save(user);
    	   tx.commit();
    	   session.close();
       }
	  @Test
		public void testGet() throws Exception {
			Session session = HibernateUtil.getsession();
			Transaction tx = session.beginTransaction();
			
			//构建主键再查询
			 Compositekeys keys=new Compositekeys();
			keys.setAddress("广东");
			keys.setUserName("peace");
			
			// 主键查询
			User user = (User) session.get(User.class, keys);
			// 测试输出
			if (user != null){
				System.out.println(user.getKeys().getUserName());
				System.out.println(user.getKeys().getAddress());
				System.out.println(user.getAge());
			}
			
			
			tx.commit();
			session.close();
		}
}
