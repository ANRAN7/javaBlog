package com.rlovep.inerceptor.demo.utils;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 
* @ClassName: JdbcUtils
* @Description: 建立数据库工具类，创建c3p0连接池
* @author peace w_peace@163.com 
* @date 14 Dec 2015 3:29:46 pm
*
 */
public class JdbcUtils {
      private static DataSource dataSource;
      static{
    	  dataSource=new ComboPooledDataSource();
      }
      public static DataSource getDataSource(){
    	  return dataSource;
      }
      //创建Dbutils常用工具类
      public static QueryRunner getQuerrRunner(){
    	  return new QueryRunner(dataSource);
      }
}
