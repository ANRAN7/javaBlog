package com.peace.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class BaseDao<T> {
	//
	private Class clazz;
	private String tableName;
	
	// 构造函数： 1. 获取当前运行类的参数化类型； 2. 获取参数化类型中实际类型的定义(class)
	public BaseDao(){
		//  this  表示当前运行类  (AccountDao/AdminDao)
		//  this.getClass()  当前运行类的字节码(AccountDao.class/AdminDao.class)
		//  this.getClass().getGenericSuperclass();  当前运行类的父类，即为BaseDao<Account>
		//                                           其实就是“参数化类型”， ParameterizedType   
	      //获得父类type  此处为参数类型 BaseDao<Account>
		Type type = this.getClass().getGenericSuperclass();
		// 强制转换为“参数化类型”  【BaseDao<Account>】
	    ParameterizedType pType=(ParameterizedType)type;
	     // 获取参数化类型中，实际类型的定义  【new Type[]{Account.class}】
	    Type[] aTypes = pType.getActualTypeArguments();
	     // 获取数据的第一个元素：Accout.class
	    clazz=(Class)aTypes[0];
	    // 表名  (与类名一样，只要获取类名就可以)
	    tableName=clazz.getSimpleName();
	}
	/**
	 * 
	* @Title: findByid 
	* @Description: 通过主键id进行查找
	* @param id
	* @return:T   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public T findByid(int id){
		/*
		 * 1. 知道封装的对象的类型
		 * 2. 表名【表名与对象名称一样， 且主键都为id】
		 * 
		 * 即，
		 * 	  ---》得到当前运行类继承的父类  BaseDao<Account>
		 *   ----》 得到Account.class
		 */
		
		String sql="select * from " + tableName + " where id=? ";//"select * from"+tableName+"where id=?";
		try {
			return Jdbc_Utils.getQurrRunner().query(sql, new BeanHandler<T>(clazz),id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 查找所有；
	 */
	public List<T> getAll(){
		String sql="select * from admin"; //+ tableName ;
		try {
			return Jdbc_Utils.getQurrRunner().query(sql, new BeanListHandler<T>(clazz));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
