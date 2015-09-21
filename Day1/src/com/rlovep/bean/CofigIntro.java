package com.rlovep.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import org.junit.Test;
/*
内省--->一个反射的变体.

内省主要解决 的问题： 把对象的属性数据封装 到对象中。
通过反射调用方法更改属性；
 */
public class CofigIntro {
	@Test
	public void getAllProperty() throws IntrospectionException{
		//Introspector 内省类
		BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
		//通过BeanInfo获取所有的属性描述其
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors(); //获取一个类中的所有属性描述器
		for(PropertyDescriptor p : descriptors){
			//获得所有get方法
			System.out.println(p.getReadMethod()); //get方法
		}
		
		
	}
	
	@Test
	public  void testProperty() throws Exception{
		Person p = new Person();
		//属性描述器
		PropertyDescriptor descriptor = new PropertyDescriptor("id", Person.class);
		//获取属性对应的get或者是set方法设置或者获取属性了。
		Method  m = descriptor.getWriteMethod();  //获取属性的set方法。
		//执行该方法设置属性值
		m.invoke(p,110);
	     //获得get方法；
		Method readMethod = descriptor.getReadMethod(); //是获取属性的get方法
		System.out.println(readMethod.invoke(p, null));
	}
}
