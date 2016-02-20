package com.rlovep.ann.util;

import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
* @ClassName: Id
* @Description: 描述一个主键字段
* @author peace w_peace@163.com 
* @date 10 Dec 2015 8:48:56 pm
*
 */
@Target({FIELD})//字段有效
@Retention(RetentionPolicy.RUNTIME)//指定注解运行时期有效
public @interface Id {

}
