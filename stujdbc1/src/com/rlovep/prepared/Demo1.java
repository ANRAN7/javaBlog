package com.rlovep.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.rlovep.jdbc.JdbcUtil;
/**
 * 
* @ClassName: Demo1
* @Description: 使用PreparedStatement执行sql语句
* @author peace w_peace@163.com 
* @date 8 Nov 2015 7:11:02 pm
*
 */
public class Demo1 {
	/**
	 * 
	* @Title: testDml 
	* @Description: 使用PreparedStatement，增删改
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void testDml(){
		Connection connection = JdbcUtil.getConnection();
		PreparedStatement statement=null;
		try {
			//增加
			String sql="insert into stu values(null,?,?)"; //?表示一个参数的占位符
			//通过连接执行sql预编译后获得PreparedStatement
			statement=connection.prepareStatement(sql);
			//设置参数值：参数位置从一开始
			statement.setString(1, "刘思思");
			statement.setString(2, "女");
			int count =statement.executeUpdate();
			System.out.println("影响了"+count+"行");
			//可以重复执行
			statement.setString(1, "wpeace");
			statement.setString(2, "男");
		    count =statement.executeUpdate();
			System.out.println("影响了"+count+"行");
			//修改 同上，
			sql="update stu set sname='my sisi' where sid in (?,?)";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, 8);
			statement.setInt(2, 9);
			count=statement.executeUpdate();
			System.out.println("影响了"+count+"行");
			//删除 同上，
			sql="delete from stu where sname like ?";
			statement=connection.prepareStatement(sql);
			statement.setString(1, "%peace%");
			count=statement.executeUpdate();
			System.out.println("影响了"+count+"行");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(connection, statement);
	}
	/**
	 * 
	* @Title: testDql 
	* @Description: 使用 PreparedStatement预编译查询；
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void testDql(){
		Connection connection = JdbcUtil.getConnection();
		PreparedStatement statement=null;
		ResultSet rs=null;
		try {
			//增加
			String sql="select * from stu where sid in (?,?,?)";//？表示一个参数的占位符
			statement=connection.prepareStatement(sql);
			//设置参数值 
	         statement.setInt(1, 1);
	         statement.setInt(2, 8);
	         statement.setInt(3, 9);
	         //执行查询返回结果
	         rs=statement.executeQuery();
	         //遍历记过
	         while(rs.next())
	         {
	        	int id=rs.getInt("sid");//获得相应的字段值
	        	String name = rs.getString("sname");
	        	String gender = rs.getString("sgender");
	        	System.out.println(id+","+name+","+gender);
	         }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(connection, statement,rs);
	}
}	