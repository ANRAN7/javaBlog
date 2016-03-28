package com.rlovep.property;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
      @Test
      public void testProperty(){
    	  ApplicationContext ac=new ClassPathXmlApplicationContext("com/rlovep/property/bean.xml");
    	  //获得通过构造器获得的属性
    	  User user=(User)ac.getBean("user1");
    	  System.out.println(user);
    	  //通过set方法获得属性
    	  user=(User)ac.getBean("user2");
    	  System.out.println(user);
    	  //通过p：#获得属性
    	  user =(User)ac.getBean("user3");
          
      }
      //非标准类的属性
      @Test
      public void testProperty1(){
    	  ApplicationContext ac=new ClassPathXmlApplicationContext("com/rlovep/property/bean.xml");
    	  //通过set方法获得属性
    	   UserAction action=(UserAction)ac.getBean("userAction1");
    	   action.execute();
    	  //通过内部bean
    	  action=(UserAction)ac.getBean("userAction2");
    	  action.execute();
    	  //通过p：#获得属性
          action=(UserAction)ac.getBean("userAction3");
          action.execute();
      }
      @Test
      public void testAuto1(){
    	  ApplicationContext ac=new ClassPathXmlApplicationContext("com/rlovep/property/auto.xml");
    	  //通过set方法获得属性
    	   UserAction action=(UserAction)ac.getBean("userAction");
    	   action.execute();
      }
      @Test
      public void testAuto2(){
    	  ApplicationContext ac=new ClassPathXmlApplicationContext("com/rlovep/property/autoByType.xml");
    	  //通过set方法获得属性
    	   UserAction action=(UserAction)ac.getBean("userAction");
    	   action.execute();
      }
}
/*//Resource resource = new ClassPathResource("appcontext.xml");
// BeanFactory factory = new XmlBeanFactory(resource);

// 用classpath路径
// ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:appcontext.xml");
// ApplicationContext factory = new ClassPathXmlApplicationContext("appcontext.xml");

// ClassPathXmlApplicationContext使用了file前缀是可以使用绝对路径的
// ApplicationContext factory = new ClassPathXmlApplicationContext("file:F:/workspace/example/src/appcontext.xml");

// 用文件系统的路径,默认指项目的根路径
// ApplicationContext factory = new FileSystemXmlApplicationContext("src/appcontext.xml");
// ApplicationContext factory = new FileSystemXmlApplicationContext("webRoot/WEB-INF/appcontext.xml");


// 使用了classpath:前缀,这样,FileSystemXmlApplicationContext也能够读取classpath下的相对路径
// ApplicationContext factory = new FileSystemXmlApplicationContext("classpath:appcontext.xml");
// ApplicationContext factory = new FileSystemXmlApplicationContext("file:F:/workspace/example/src/appcontext.xml");

// 不加file前缀
ApplicationContext factory = new FileSystemXmlApplicationContext("F:/workspace/example/src/appcontext.xml");
IHelloWorld hw = (IHelloWorld)factory.getBean("helloworldbean");
log.info(hw.getContent("luoshifei"));*/