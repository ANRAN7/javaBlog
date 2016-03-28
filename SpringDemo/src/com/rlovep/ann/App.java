package com.rlovep.ann;



import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class App {
    
		@Test
		public void testAnn(){
			ApplicationContext ac=new ClassPathXmlApplicationContext("com/rlovep/ann/bean.xml");
			UserAction userAction=(UserAction)ac.getBean("userAction");
			userAction.execute();
		}
}
