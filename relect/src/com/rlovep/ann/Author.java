package com.rlovep.ann;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: Author
* @Description: 自定义注解：Author
* @author peace w_peace@163.com 
* @date 8 Dec 2015 5:02:03 pm
*
 */
//元注解：Target  定义自定义注解的可用范围：
@Target({TYPE,FIELD , METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
//元注解：Retention  指定注解的声明周期
@Retention(RetentionPolicy.RUNTIME)   // 字节码级别有效
public @interface Author {
	/*
	 * 注解修修饰只能为默认 和public
	 * 不能有主体
	 */

     String authorName() default "peace";//给定默认值，不需要真正实现函数，其实就是变量；
     int age() default 30;
     String remark();
}
