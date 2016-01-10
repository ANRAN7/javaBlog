package com.rlovep.i18n;

import java.util.Locale;

/**
 * 
* @ClassName: ILocate
* @Description: 测试locale
* @author peace w_peace@163.com 
* @date 20 Nov 2015 3:23:25 pm
*
 */
public class ILocate {
	public static void main(String[] args) {
		//获得系统默认的语言环境
		Locale locale=Locale.getDefault();
		//获得国家
		System.out.println(locale.getCountry());
		//获得语言
		System.out.println(locale.getLanguage());
	}     
}
