package com.rlovep.ann.util;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: Table
* @Description: 描述表名称
* @author peace w_peace@163.com 
* @date 10 Dec 2015 8:52:20 pm
*
 */
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
   String tableName();
}
