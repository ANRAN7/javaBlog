package com.rlovep.callable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.rlovep.jdbc.JdbcUtil;

/**
 * 
* @ClassName: Demo1
* @Description: 使用CablleStatement调用存储过程
* @author peace w_peace@163.com 
* @date 8 Nov 2015 8:03:33 pm
*
 */
public class Demo1 {
	//创建过程的sql语句
	/*delimiter $
	create procedure pro_findById(in id int)
	begin
		select * from stu where sid=id;
	end $*/
	/**
	 * 
	* @Title: testCallIn 
	* @Description:通过CallableStatement执行传入输入参数的过程
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void testCallIn(){
		
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			conn=JdbcUtil.getConnection();
			//调用过程的sql语句
			String sql="call pro_findById(?)";
			//过得调用过程的CallableStatement
			stmt = conn.prepareCall(sql);
			//设置位置参数
			stmt.setInt(1, 8);
			//调用过程：使用executeQuery()
			 rs = stmt.executeQuery();
			 //便利结果
			 if(rs.next())
			 {
				   int id = rs.getInt("sid");
					String name = rs.getString("sname");
					String gender = rs.getString("sgender");
					System.out.println(id+","+name+","+gender);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JdbcUtil.close(conn, stmt, rs);
	}
	//建立过程的sql
	/*delimiter $
	create procedure pro_findById2(in id int , out Iname varchar(20))
	begin
	     select sname into Iname from stu where sid=id;
	end $*/
	/**
	 * 
	* @Title: testCallOut 
	* @Description: CallableStatement调用传入输入输出参数的过程
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
     public void testCallOut(){
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try {
			//获得连接
			conn=JdbcUtil.getConnection();
			//执行过程的sql语句
			String sql="call pro_findById2(?,?)";
			//获得CallableStatement
			stmt = conn.prepareCall(sql);
			//设置位置参数
			stmt.setInt(1, 8);
			//对于输出参数的设置：第一个参数为输出参数的位置，第二个参数为输出参数类型
			stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			//执行，并获得结果
			 rs = stmt.executeQuery();
			 //输出rs
			 System.out.println(rs);
			 //通过调用CallableStatement。获得输出参数的，通过输出参数的位置获得
			 System.out.println(stmt.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcUtil.close(conn, stmt, rs);
     }
}
