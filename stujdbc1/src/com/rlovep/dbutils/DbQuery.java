package com.rlovep.dbutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.rlovep.jdbc.JdbcUtil;
import com.rlovep.selfDao.Admin;

public class DbQuery {
	private Connection connection=null;
	@Test
	/**
	 * 
	* @Title: testsingleQuery 
	* @Description: 自定义封装数据 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
   public void testsingleQuery(){
	   String sql="select * from admin where id=?;";
	   //获取
	   connection=JdbcUtil.getConnection();
	   //创建Dbutils核心工具类
	   QueryRunner qr=new QueryRunner();
	  //查询
	   try {
		   //将查到的数据封装一个Admin对象
		 //参数依次为：连接，sql语句，结果处理器，位置参数
		Admin admin=qr.query(connection,sql, new ResultSetHandler<Admin>(){//结果处理器是编写
			   public Admin handle(ResultSet rs){//将结果进行封装
				   try {
					if(rs.next()){
						Admin admin = new Admin();
						admin.setId(rs.getInt("id"));
						admin.setUserName(rs.getString("userName"));
						admin.setPwd(rs.getString("pwd"));
						return admin;
					   }
				} catch (SQLException e) {
					e.printStackTrace();
				}
				   return null;
			   }
		   },4);
		//输出与关闭连接
		System.out.println(admin);
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   //
   }
	
	@Test
	/**
	 * 
	* @Title: testQueryOne 
	* @Description: 使用组件提供的结果集对象封装数据。 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public void testQueryOne(){
		String sql="select * from admin where id=?";
		//获取连接
		connection=JdbcUtil.getConnection();
		//创建Dbutils核心工具类
		QueryRunner qr=new QueryRunner();
		//查询返回单个对象
		try {
			//使用beanhandle进行封装
			//参数依次为：连接，sql语句，结果处理器，位置参数
			//查下你结果封装到Admin
			Admin admin=qr.query(connection,sql, new BeanHandler<Admin>(Admin.class), 4);
			System.out.println(admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		  try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	@Test
	/**
	 * 
	* @Title: testArry 
	* @Description: 测试几个有用的处理函数 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public void testArry(){
		String sql="select * from admin";
		connection=JdbcUtil.getConnection();
		QueryRunner qr=new QueryRunner();
		
		try {
			//ArrayHandler, 查询返回结果记录的第一行，封装对对象数组, 即返回：Object[]
			Object[] objects = qr.query(connection, sql, new ArrayHandler());
		    for(Object o:objects){
		    	System.out.println(o); }
		    System.out.println(">>>>>>>>>>>");
		    //ArrayListHandler, 把查询的每一行都封装为对象数组，再添加到list集合中
		    List<Object[]> list = qr.query(connection, sql, new ArrayListHandler());
		    for(Object[] objects2:list){
		    	for(Object o:objects2){
			    	System.out.println(o); }
		    }
		    System.out.println(">>>>>>>>>>>");
	    //ScalarHandler 查询返回结果记录的第一行的第一列  (在聚合函数统计的时候用)
		 int id = qr.query(connection, sql, new ScalarHandler<>());
		 System.out.println(id);
		 System.out.println(">>>>>>>>>>>");
		 //MapHandler  查询返回结果的第一条记录封装为map
		 Map<String, Object> query = qr.query(connection, sql, new MapHandler());
		 for(String s:query.keySet()){
			 System.out.println(query.get(s));
		 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
