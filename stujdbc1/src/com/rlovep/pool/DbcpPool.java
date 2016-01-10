package com.rlovep.pool;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

/**
 * 
* @ClassName: DbcpPool
* @Description: 测试dbcp连接池技术
* @author peace w_peace@163.com 
* @date 15 Nov 2015 6:00:17 pm
*
 */
public class DbcpPool {
	@Test
    public void testDbcp(){ 
    	//Dbcp连接池核心类
		BasicDataSource dataSource=new BasicDataSource();
		//连接池参数配置：初始化连接数、最大连接数 / 连接字符串、驱动、用户、密码
		dataSource.setUrl("jdbc:mysql://localhost:3306/day01?useUnicode=true&characterEncoding=UTF8");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("126165");
		dataSource.setInitialSize(3);  // 初始化连接
		dataSource.setMaxActive(6);	  // 最大连接
		dataSource.setMaxIdle(3000);   // 最大空闲时间
    	
		try {
			//获取连接
			Connection connection = dataSource.getConnection();
			//关闭
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	@Test
	// 2. 【推荐】配置方式实现连接池  ,  便于维护
	public void testProp() throws Exception {
		// 加载prop配置文件
		Properties prop = new Properties();
		// 获取文件流
		InputStream inStream = DbcpPool.class.getResourceAsStream("/db.properties");
		// 加载属性配置文件
		prop.load(inStream);
		// 根据prop配置，直接创建数据源对象
		DataSource dataSouce = BasicDataSourceFactory.createDataSource(prop);
		
		// 获取连接
		Connection con = dataSouce.getConnection();
		con.prepareStatement("delete from admin where sid=4").executeUpdate();
		// 关闭
		con.close();
	}
}
