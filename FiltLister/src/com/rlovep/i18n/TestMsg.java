package com.rlovep.i18n;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class TestMsg {
	/**
	 * 
	* @Title: testMsg 
	* @Description: 测试静态文字的国际化 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
     public void testMsg(){
		 /**
		  * 在类路径下建立属性文件：命名格式：b)基础名_语言简称_国家简称.properties
		  * 例如：msg_zh_CN.properties     存储所有中文 msg_en_US.properties    存储所有英文
		  */
    	 Locale locale=Locale.US;
    	 ResourceBundle rb=ResourceBundle.getBundle("com.rlovep.i18n.msg", locale);
    	 System.out.println(rb.getString("username"));
    	 System.out.println(rb.getString("hello"));
    	 //自动通过传入的locale选择属性文件：
    	 locale=Locale.CHINA;
    	 rb=ResourceBundle.getBundle("com.rlovep.i18n.msg", locale);
    	 System.out.println(rb.getString("username"));
    	 System.out.println(rb.getString("hello"));
	}
	/**
	 * 
	* @Title: testI18nDyn 
	* @Description: 测试动态文本国际化 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void testI18nDyn(){
		//国际化货币：
		Locale locale=Locale.US;
		//数据准备：
		double money=100.67;
		//工具类
		NumberFormat nf=NumberFormat.getCurrencyInstance(locale);
		//国际化货币
			System.out.println(nf.format(money));
		// 国际化数值
	        locale = Locale.CHINA;
			 nf = NumberFormat.getNumberInstance(Locale.US);
			String str = nf.format(1000000000);
			System.out.println(str);
			// 日期格式
			int dateStyle = DateFormat.SHORT;
			// 时间格式
			int timeStyle = DateFormat.SHORT;
			// 工具类
			DateFormat df = 
				DateFormat.getDateTimeInstance(dateStyle, timeStyle, Locale.CHINA);		
			String date = df.format(new Date());
			try {
				//反向解析
				Date d = df.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			System.out.println(date);
			
	}
}
