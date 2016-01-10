package com.rlovep.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.mysql.jdbc.ResultSetMetaData;
import com.rlovep.jdbc.JdbcUtil;

/**
 * 
* @ClassName: Demo
* @Description: 获取元数据，接着获得信息
* @author peace w_peace@163.com 
* @date 14 Nov 2015 9:02:09 pm
*
 */
public class Demo {
	@Test
	/**
	 * 
	* @Title: testMeaDat 
	* @Description: 获取数据库的信息 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public void testMeaDat(){
		Connection connection=JdbcUtil.getConnection();
		try {
			DatabaseMetaData metaData = connection.getMetaData();
			System.out.println(metaData.getDriverName());
			System.out.println(metaData.getURL());
			System.out.println(metaData.getUserName());
			System.out.println(metaData.getDatabaseProductName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	/**
	 * 
	* @Title: testParams 
	* @Description: 参数元数据 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public void testParams(){
		Connection connection=JdbcUtil.getConnection();
		String sql="select * from dept where id=?";
		PreparedStatement statement=null;
        try {
			statement=connection.prepareStatement(sql);
			statement.setInt(1, 2);
			ParameterMetaData parameterMetaData = statement.getParameterMetaData();
			
			System.out.println("参数个数"+parameterMetaData.getParameterCount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		 JdbcUtil.close(connection, statement);
		}
        
	}
	@Test
	/**
	 * 
	* @Title: testRs 
	* @Description: 结果集元数据 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public void testRs(){
		Connection connection=JdbcUtil.getConnection();
		String sql="select * from dept";
		PreparedStatement statement=null;
		ResultSet rs=null;
	
        try {
			statement=connection.prepareStatement(sql);
			rs=statement.executeQuery();
			// 得到结果集元数据(目标：通过结果集元数据，得到列的名称)
           java.sql.ResultSetMetaData metaData = rs.getMetaData();
           while(rs.next())
           {
        	   //获取列的个数
        	   int count=metaData.getColumnCount();
        	   for(int i=0;i<count;i++){
        		   //列名获得
        		   String name=metaData.getColumnName(i+1);
        		   //通过列名获得价值
        		   Object object = rs.getObject(name);
        		   //输出价值
        		   System.out.println(name+object);
        	   }
           }
           
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, statement, rs);
		}
        
	}
}
