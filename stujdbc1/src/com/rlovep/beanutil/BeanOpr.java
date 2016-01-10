package com.rlovep.beanutil;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.junit.Test;

public class BeanOpr {
	private String name;
	
	@Test
	/**
	 * 
	* @Title: testHello 
	* @Description: beanutiils拷贝的介绍 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public void testHello(){
		Admin admin=new Admin();
		try {
			//获得属性方法：
			System.out.println(BeanUtils.getProperty(admin,"userName"));
			//拷贝属性
			BeanUtils.copyProperty(admin, "usetName", "peace");
			//类似于设置属性
			BeanUtils.setProperty(admin, "id", 001);
			//对象的拷贝
			Admin admin2=new Admin();
			BeanUtils.copyProperties(admin2, admin);
			//输出两个admin
			System.out.println(admin);
			System.out.println(admin2);
			//map数据，拷贝到对象中
			Map<String, Object> map=new HashMap<>();
			map.put("userName","peace2");
			map.put("age", 22);
			map.put("id", 002);
			map.put("pwd", 123456);
			//通过Map拷贝：
			BeanUtils.populate(admin,map);
			System.out.println(admin);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/**
	 * 
	* @Title: testRegist 
	* @Description: 实现对不支持的类进行转换。 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	  public void testRegist(){
		
		// 注册日期类型转换器：1， 自定义的方式
		  ConvertUtils.register(new Converter() {
			  /**
			   * 转换函数，实现对date的转换。
			   */
			@Override
			public Object convert(Class type, Object value) {
		         //判断是否为Date类型
				if(type!=Date.class)
				   return null;
				//判断是否为空
				if(value==null||"".equals(value.toString().trim()))
				       return null;
			try {
				//转换方式
				SimpleDateFormat date=new SimpleDateFormat("yyyy-mm-dd");
			return date.parse(value.toString());
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			}
		}, Date.class);
		  //执行
		  Admin admin=new Admin();
			Map<String, Object> map=new HashMap<>();
			map.put("userName","peace2");
			map.put("age", 22);
			map.put("id", 002);
			map.put("pwd", 123456);
			map.put("birth", new Date(2015, 10, 9));
			try {
				BeanUtils.populate(admin,map);
				System.out.println(admin);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	 @Test
	 /**
	  * 
	 * @Title: testRigest2 
	 * @Description: 使用提供的date类型转换器 
	 * @return:void   
	 * @throws 
	 * @author peace w_peace@163.com
	  */
	 public void testRigest2(){
		 ConvertUtils.register(new DateConverter(), Date.class);
		 //执行
		  Admin admin=new Admin();
			Map<String, Object> map=new HashMap<>();
			map.put("userName","peace2");
			map.put("age", 22);
			map.put("id", 002);
			map.put("pwd", 123456);
			map.put("birth", new Date(2015, 10, 9));
			try {
				BeanUtils.populate(admin,map);
				System.out.println(admin);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
   }
