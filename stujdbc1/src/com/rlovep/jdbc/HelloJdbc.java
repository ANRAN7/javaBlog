package com.rlovep.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

/**
 * 
* @ClassName: HelloJdbc
* @Description: 数据库jdbc学习入门 此去都是测试函数使用junit包测试；
* @author peace w_peace@163.com 
* @date 7 Nov 2015 9:09:02 pm
*
 */
public class HelloJdbc {
	//连接到数据库的url
	private String url="jdbc:mysql://localhost:3306/day01";
					 //jdc协议：数据库子协议：主机：端口/连接的数据库
	private String user="root";//用户名
	private String password="126165";//密码
	/**
	* @Title: testJdbc1 
	* @Description: 测试jdbc的连接 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void testJdbc1(){
		Driver driver=null;//定义驱动引用 使用java.sql中的接口
		Connection conn=null;//连接引用 使用java.sql中的接口
		try {
			driver=new com.mysql.jdbc.Driver();//实例化mysql的驱动，辞去用的是mysql中的类
			//设置用户名和密码
			Properties props = new Properties();//创建属性
			props.setProperty("user", user);//设置user的值
			props.setProperty("password", password);//设置密码的值
		
			//2.连接数据库，返回连接对象
			 conn= driver.connect(url, props);//获得连接，通过url，账户，密码
			 System.out.println(conn);//输出连接结果
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//关闭连接
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * 
	* @Title: testjdbc2 
	* @Description: DriverManager 注册驱动，获得连接
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void testjdbc2(){
		Driver driver=null;
		Connection conn=null;
		try {
			//实例化驱动
			driver=new com.mysql.jdbc.Driver();
			//注册驱动
			DriverManager.registerDriver(driver);
			//获得连接
			conn=DriverManager.getConnection(url, user, password);
			//输出结果
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//关闭连接
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
	}
	/**
	 * 
	* @Title: testJdbc3 
	* @Description: 使用加载驱动程序类，来注册驱动程序。推荐使用 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void testJdbc3(){
		
		Connection conn=null;
		try {
			//通过得到字节码对象的方式加载静态代码块，从而注册驱动程序
			//此去会加载mysql Driver中的静态代码快部分：DriverManager.registerDriver(driver);
			Class.forName("com.mysql.jdbc.Driver");
			//获得连接
			 conn = DriverManager.getConnection(url,user,password);
			 //输出连接结果：
			 System.out.println(conn);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			//关闭连接
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
	}
	/**
	 * 
	* @Title: testJdbutil 
	* @Description: 通过自己编写的工具类连接 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	@Test
	public void testJdbutil(){
		//调用工具类中的静态函数
		Connection connection = JdbcUtil.getConnection();
		//输出连接结果
		System.out.println(connection);
		//关闭
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
