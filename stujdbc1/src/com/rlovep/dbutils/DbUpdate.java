package com.rlovep.dbutils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.rlovep.jdbc.JdbcUtil;

/**
 * 
* @ClassName: DbUpdate
* @Description: 测试Dbutils的update函数
* @author peace w_peace@163.com 
* @date 15 Nov 2015 4:02:30 pm
*
 */
public class DbUpdate {
  private Connection connection;
  	@Test
	public void testUpdate(){
		String sql="delete from admin where sid=?";
		String sql2="insert into admin(username,pwd) values(?,?);";
		connection=JdbcUtil.getConnection();
		QueryRunner queryRunner=new QueryRunner();
		try {
			//简单测试update函数
			queryRunner.update(connection, sql, 2);
			//批处理  第三个参数为位置参数。
			queryRunner.batch(connection, sql2, new Object[][]{{"jack1","888"},{"jack2","889"}});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DbUtils.close(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
