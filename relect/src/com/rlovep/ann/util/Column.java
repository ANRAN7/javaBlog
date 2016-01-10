package com.rlovep.ann.util;

import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 
* @ClassName: Column
* @Description: 描述一个字段
* @author peace w_peace@163.com 
* @date 10 Dec 2015 8:45:32 pm
*
 */
@Target({FIELD})//在字段时有效
@Retention(RetentionPolicy.RUNTIME)//指定注解在运行时期有效
public @interface Column {
    String columnName();
}
