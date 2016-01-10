package com.peace.generic;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Jdbc_Utils {
	
	 //初始化连接池
	 private static DataSource dataSource;
	 static {
		 dataSource=new ComboPooledDataSource();
		 	 }
      public static DataSource getDataSource(){
    	  return dataSource;
      }
      
      /**
       * 创建DbUtils常用工具类对象
       */
      public static QueryRunner getQurrRunner(){
    	  return new QueryRunner(dataSource);
      }
}
