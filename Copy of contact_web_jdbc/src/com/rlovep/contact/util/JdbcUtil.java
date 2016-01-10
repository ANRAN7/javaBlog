package com.rlovep.contact.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 
* @ClassName: JdbcUtil
* @Description: jdbc工具类
* @author peace w_peace@163.com 
* @date 7 Nov 2015 10:19:46 pm
*
 */
public class JdbcUtil {
    private static String url=null;
    private static String user=null;
    private static String password=null;
    private static String driverclass=null;
    static{
    	Properties properties=new Properties();
    	try {
			InputStream in=JdbcUtil.class.getResourceAsStream("/db.properties");
			properties.load(in);
			
			url=properties.getProperty("url");
			user=properties.getProperty("user");
			password=properties.getProperty("password");
			driverclass=properties.getProperty("driverClass");
			Class.forName(driverclass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("驱程程序注册出错io");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("驱程程序注册出错class");
		}
    }
    public static Connection getConnection(){
    	Connection conn=null;
    	
    	try {
			conn=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取连接出错");
		}
    	return conn;
    }
    public static void close(Connection connection,Statement statement){
    	if(statement!=null){
    		try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("关闭连接失败state");
			}
    	}
    	if(connection!=null){
    		try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭连接失败conn");
			}
    	}
    }
    public static void close(Connection connection,Statement statement,ResultSet resultSet){
    	if(resultSet!=null)
			try {
				resultSet.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("关闭连接失败rs");
			}
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭连接失败state");
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭连接失败conn");
			}
		}
    }
    public static void main(String[] args) {
    	Connection connection = JdbcUtil.getConnection();
		System.out.println(connection);
		if(connection!=null)
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
