package com.rlovep.pool;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
* @ClassName: CpPool
* @Description: 测试c3p0连接池技术
* @author peace w_peace@163.com 
* @date 15 Nov 2015 6:14:30 pm
*
 */
public class CpPool {
	@Test
    public void testCp3(){
    	//创建核心工具类
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		// 设置连接参数：url、驱动、用户密码、初始连接数、最大连接数
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/day01?useUnicode=true&characterEncoding=UTF8");
		
		dataSource.setUser("root");
		dataSource.setPassword("126165");
		dataSource.setInitialPoolSize(3);
		dataSource.setMaxPoolSize(6);
		dataSource.setMaxIdleTime(1000);
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			// ---> 从连接池对象中，获取连接对象
			Connection con = dataSource.getConnection();
			// 执行更新
			con.prepareStatement("delete from admin where id=10").executeUpdate();
			// 关闭
			con.close();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@Test
	//XML配置方式，使用C3P0连接池管理连接
	public void testXML() throws Exception {
		// 创建c3p0连接池核心工具类
		// 自动加载src下c3p0的配置文件【c3p0-config.xml】
		ComboPooledDataSource dataSource = new ComboPooledDataSource();// 使用默认的配置
		PreparedStatement pstmt = null;
		
		// 获取连接
		Connection con = dataSource.getConnection();
		for (int i=1; i<11;i++){
			String sql = "insert into employ(empName,eid) values(?,?)";
			// 执行更新
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "Rose" + i);
			pstmt.setInt(2, 1);
			pstmt.executeUpdate();
		}
		pstmt.close();
		// 关闭
		con.close();
		
	}
}
