package com.rlovep.ann;

import java.lang.reflect.Method;

import com.peace.generic.account;

public class Test {
	@Author(authorName="peace",age=20,remark="1")
    @org.junit.Test
    public void test()throws Exception{
    	Class clazz=Test.class;
    	Method m=clazz.getMethod("test");
    	
    	Author author = m.getAnnotation(Author.class);
    	
    	// 获取输出注解信息
    	System.out.println(author.authorName());
    	System.out.println(author.age());
    	System.out.println(author.remark());
    }
	 @org.junit.Test
	public void testMain() throws Exception {
		test();
	}

}
