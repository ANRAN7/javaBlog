package cn.itcast.introspector;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

/*
 BeanUtils：
 
 BeanUtils主要解决 的问题： 把对象的属性数据封装 到对象中。
 
 BeanUtils的好处：
 	1. BeanUtils设置属性值的时候，如果属性是基本数据 类型，BeanUtils会自动帮我转换数据类型。
  	2. BeanUtils设置属性值的时候底层也是依赖于get或者Set方法设置以及获取属性值的。
  	3. BeanUtils设置属性值,如果设置的属性是其他的引用 类型数据，那么这时候必须要注册一个类型转换器。
  	
 
 

 BeanUtilss使用的步骤：
 	1. 导包commons-logging.jar 、 commons-beanutils-1.8.0.jar

 */
public class Demo3 {

	public static void main(String[] args) throws Exception {
		//从文件中读取到的数据都是字符串的数据，或者是表单提交的数据获取到的时候也是字符串的数据。
		String id ="110";
		String name="陈其";
		String salary = "1000.0";
		String birthday = "2013-12-10";
		
		
		//注册一个类型转换器
		ConvertUtils.register(new Converter() {

			@Override
			public Object convert(Class type, Object value) { // type : 目前所遇到的数据类型。  value :目前参数的值。
				Date date = null;
				try{
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					date = dateFormat.parse((String)value);
				}catch(Exception e){
					e.printStackTrace();
				}
				return date;
			}
			
		}, Date.class);
		
		
		Emp  e = new Emp();
		BeanUtils.setProperty(e, "id", id);
		BeanUtils.setProperty(e, "name",name);
		BeanUtils.setProperty(e, "salary",salary);
		BeanUtils.setProperty(e, "birthday",birthday);
		
		
		System.out.println(e);
		
		
		
		
	}
	
}
