package com.rlovep.hello;



import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class App {
     //1.通过工程类得到IOC容器创建的对象
	@Test
	public void testFac(){
		//获得资源
		Resource resource = new ClassPathResource("com/rlovep/hello/applicationContext.xml");
		//创建爱你工程,该方法以及不推荐使用
		BeanFactory factory = new XmlBeanFactory(resource);
		System.out.println(11);
		//得到容器对象
		User user = (User)factory.getBean("user");//此去会执行User的构造器
		System.out.println(22);
		user.setId(3);
		System.out.println(user.getId());
	}
	//2.直接得到Ioc容器对象
	@Test
	public void testAc(){
		//得到Ioc容器对象
		ApplicationContext ac = new  ClassPathXmlApplicationContext("com/rlovep/hello/applicationContext.xml");//此去会执行user的构造器
		System.out.println(11);
		//从容器中获得bean
		User user=(User)ac.getBean("user");
		System.out.println(22);
		user.setId(3);
		System.out.println(user.getId());
		
		
	}
	//2.直接得到Ioc容器对象
		@Test
		public void testIoc(){
			// 得到IOC容器对象  【用实现类，因为要调用销毁的方法】
			ClassPathXmlApplicationContext ac = new  ClassPathXmlApplicationContext("com/rlovep/hello/applicationContext.xml");//此去会执行user的构造器
			System.out.println(11);
			//从容器中获得bean
			User user=(User)ac.getBean("user1");
			User user2=(User)ac.getBean("user1");
			System.out.println(22);
			user.setId(3);
			System.out.println(user.getId());
			//单例，对象一样
			System.out.println(user);
			System.out.println(user2);
			//销毁容器对象
			ac.destroy();
		}
		@Test
		public void testBean(){
			ApplicationContext ac=new ClassPathXmlApplicationContext("com/rlovep/hello/bean.xml");
			//获得无参数构造的对象
			User user=(User)ac.getBean("user1");
			System.out.println("使用无参数构造器的User："+user);
			System.out.println("---------");
			//获得有参数构造的对象
			user=(User)ac.getBean("user2");
			System.out.println("使用有参数构造器的User："+user);
			////获得引用外部变量构造的对象
			user=(User)ac.getBean("user3");
			System.out.println("使用引用外部变量构造的User："+user);
			//通过工厂实例方法获得
			user=(User)ac.getBean("user4");
			System.out.println("通过工厂实例方法获得："+user);
			//通过工厂静态方法获得
			user=(User)ac.getBean("user5");
			System.out.println("通过工厂静态方法获得："+user);
			//通过对象名字属性获得
			user=(User)ac.getBean("2user");
			System.out.println("通过对象名字属性获得："+user);
		}
}
