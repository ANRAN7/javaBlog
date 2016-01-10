package com.rlovep.utile;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
* @ClassName: JdbcUtiles
* @Description: 数据库连接工具
* @author peace w_peace@163.com 
* @date 16 Nov 2015 11:25:31 am
*
 */
public class JdbcUtiles {
  
	/**
	 * 初始化c3p0，调用配置文件初始化。
	 */
	private static ComboPooledDataSource dataSource;
	static{
		dataSource=new ComboPooledDataSource();
	}
	/**
	 * 
	* @Title: getQureyRunner 
	* @Description: 获得Dbutiles核心工具类。
	* @return:QueryRunner   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public static QueryRunner getQureyRunner(){
		// 在创建QueryRunner对象的时候，如果传入了数据源对象；
		// 那么在使用QueryRunner对象方法的时候，就不需要传入连接对象；
		// 会自动从数据源中获取连接(不用关闭连接)
		return new QueryRunner(dataSource);
	}
}
