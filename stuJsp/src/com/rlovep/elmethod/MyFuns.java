package com.rlovep.elmethod;
/**
 * 
* @ClassName: MyFuns
* @Description: el标签调用函数的类
* @author peace w_peace@163.com 
* @date 21 Oct 2015 3:38:02 pm
*
 */
public class MyFuns {
	public static String reverse(String str)
	{
		return new StringBuffer(str).reverse().toString();
	}
	public static int count(String str)
	{
		return str.length();
	}
}
