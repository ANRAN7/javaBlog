package com.rlovep.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义连接池, 管理连接
 * 代码实现：
	1.  MyPool.java  连接池类，   
	2.  指定全局参数：  初始化数目、最大连接数、当前连接 连接池集合
	3.  构造函数：循环创建3个连接
	4.  写一个创建连接的方法
	5.  获取连接
	------>  判断： 池中有连接， 直接拿
	 ------>                池中没有连接，
	------>                    判断，是否达到最大连接数； 达到，抛出异常；没有达到最大连接数，
			                   没有达到创建新的连接
	6. 释放连接
	 ------->  连接放回集合中(..)
 *
 */
public class Mypool {
	private int init_count = 3;		// 初始化连接数目
	private int max_count = 8;		// 最大连接数
	private int current_count = 0;  // 记录当前连接数
    //list存放连接：连接池
	private LinkedList<Connection> pool=new LinkedList<>();
	//构造函数，初始化连接池
	public Mypool(){
		for(int i=0;i<init_count;i++)
		{
			//记录当前的连接数
			current_count++;
			//创建连接对象并加入连接池
			pool.add(createConnection());
		}
	}
	//创建一个新的连接的方法
	private Connection createConnection(){
		try {
			//使用mysql的驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获得连接
          final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/day01?useUnicode=true&characterEncoding=UTF8", 
        		                                                   "root", "126165");
			//对connection建立代理
            //对接口的close方法进行扩展，添加额外的用户需要的业务逻辑！
          Connection proxy=(Connection)Proxy.newProxyInstance(connection.getClass().getClassLoader(),//类加载器
        		                          new Class[]{Connection.class},//获得对象的接口
        		                            new InvocationHandler() {
												@Override
												public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
												     //方法返回值
													Object result=null;
												     //当前执行方法的方法名
													String methodName=method.getName();
													//判断是否为close方法：
													if("close".equals(methodName)){
														System.out.println("执行close方法》》");
														//连接放入连接池
														pool.add(connection);
														System.out.println("执行完close方法，当前连接放入连接池");
													}else{
														//调用目标对象方法
														result=method.invoke(connection,args);
													}
													return result;
												}
											} );
          return proxy;//返回代理连接；
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//获得一个连接
	public Connection getConnection(){
		//如果连接池不为空，则直接拿
		if(!pool.isEmpty()){
			return pool.removeFirst();
		}
		//如果连接池为空，且当前小于最大连接数，就创建连接
		if(current_count<max_count){
			current_count++;
		  return createConnection();
		}
		//如果当前已经达到最大连接数，抛出异常
		throw new RuntimeException("当前连接已经达到最大连接数目 ！");
	}
	//释放连接：连接放回连接池中
	public void realeaseConnection(Connection con) {
		// 池的数目如果小于初始化连接，就放入池中
		if(con==null)throw new RuntimeException("连接为空");
		if (pool.size() < init_count){
			pool.addLast(con);
		} else {
				current_count--;
		}
	}
	public static void main(String[] args) {
		Mypool pMypool=new Mypool();
		System.out.println("当前连接数："+pMypool.current_count);
		//使用连接
		Connection connection1 = pMypool.getConnection();
		Connection connection2 = pMypool.getConnection();
		Connection connection3 = pMypool.getConnection();
		Connection connection4 = pMypool.getConnection();
		Connection connection5 = pMypool.getConnection();
		pMypool.realeaseConnection(connection1);
		connection1=null;
		pMypool.realeaseConnection(connection2);
		connection2=null;
		pMypool.realeaseConnection(connection3);
		connection3=null;
		pMypool.realeaseConnection(connection4);
		connection4=null;
		System.out.println("当前连接数："+pMypool.current_count);
		System.out.println("连接池：" + pMypool.pool.size());      // 0
	}
}
