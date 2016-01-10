package com.rlovep.jdbc;

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
	//定义需要用到成员
    private static String url=null;//url
    private static String user=null;//用户
    private static String password=null;//密码
    private static String driverclass=null;//驱动类
    //使用静态代码快完成初始化
    static{
    	//创建属性类
    	Properties properties=new Properties();
    	try {
    		/**
			 *  . 代表java命令运行的目录
			 *  在java项目下，. java命令的运行目录从项目的根目录开始
			 *  在web项目下，  . java命令的而运行目录从tomcat/bin目录开始
			 *  所以不能使用点.
			 */
			//FileInputStream in = new FileInputStream("./src/db.properties");
			
			/**
			 * 使用类路径的读取方式
			 *  / : 斜杠表示classpath的根目录
			 *     在java项目下，classpath的根目录从bin目录开始
			 *     在web项目下，classpath的根目录从WEB-INF/classes目录开始
			 */
			InputStream in=JdbcUtil.class.getResourceAsStream("/db.properties");
			//加载属性文件
			properties.load(in);
			//获得相应的内容
			url=properties.getProperty("url");
			user=properties.getProperty("username");
			password=properties.getProperty("password");
			driverclass=properties.getProperty("driverClassName");
			//注册该类。
			Class.forName(driverclass);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("驱程程序注册出错io");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("驱程程序注册出错class");
		}
    }
    /**
     * 
    * @Title: getConnection 
    * @Description: 获得连接的方法
    * @return 
    * @return:Connection   
    * @throws 
    * @author peace w_peace@163.com
     */
    public static Connection getConnection(){
    	Connection conn=null;
    	
    	try {
    		//调用DriverManager.getConnection方法获得连接
			conn=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取连接出错");
		}
    	return conn;
    }
    //关闭连接
    public static void close(Connection connection,Statement statement){
    	if(statement!=null){
    		try {
				statement.close();
				statement=null;//给垃圾回收器回收
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭连接失败state");
			}
    	}
    	if(connection!=null){
    		try {
				connection.close();
				connection=null;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭连接失败conn");
			}
    	}
    }
    //关闭连接
    public static void close(Connection connection,Statement statement,ResultSet resultSet){
    	if(resultSet!=null)
			try {
				resultSet.close();
				resultSet=null;
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("关闭连接失败rs");
			}
		if(statement!=null){
			try {
				statement.close();
				statement=null;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭连接失败state");
			}
		}
		if(connection!=null){
			try {
				connection.close();
				connection=null;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭连接失败conn");
			}
		}
    }
    //测试用的函数
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
