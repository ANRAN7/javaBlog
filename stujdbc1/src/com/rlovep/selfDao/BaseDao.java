package com.rlovep.selfDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.management.Query;

import org.apache.commons.beanutils.BeanUtils;

import com.rlovep.jdbc.JdbcUtil;

/**
 * 
* @ClassName: BaseDao
* @Description: 通用的dao，自己写的所有的dao都继承此类;有两个方法：更新和查询
* @author peace w_peace@163.com 
* @date 14 Nov 2015 9:54:38 pm
*
 */
public class BaseDao {
	// 初始化参数
		private Connection con;
		private PreparedStatement pstmt;
		private ResultSet rs;
		/**
		 * 
		* @Title: update 
		* @Description: 更新的通用方法
		* @param sql 更新的sql语句(update/insert/delete)
		* @param paramsValue sql语句中占位符对应的值(如果没有占位符，传入null)
		* @return:void   
		* @throws 
		* @author peace w_peace@163.com
		 */
	public void update(String sql,Object[] paramsValue){	  
		//获取连接
	   con=JdbcUtil.getConnection();
	   try {
		// 创建执行命令的stmt对象
		pstmt=con.prepareStatement(sql);
		// 参数元数据： 得到占位符参数的个数
		int count=pstmt.getParameterMetaData().getParameterCount();
		// 设置占位符参数的值
		if(paramsValue !=null&& paramsValue.length>0){
			// 循环给参数赋值
			for(int i=1;i<=count;i++){
				pstmt.setObject(i, paramsValue[i-1]);
			}
		}
		// 执行更新
		pstmt.executeUpdate();
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}finally {
		JdbcUtil.close(con, pstmt);
	}
   
	}
	/**
	 * 
	* @Title: query 
	* @Description: 通用查询方法
	* @param sql
	* @param paramsValue
	* @param clazz
	* @return:List<T>   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public <T> List<T> query(String sql,Object[] paramsValue,Class<T> clazz){
		//返回的集合
		List<T>list=new ArrayList<>();
		//获得连接
		 con=JdbcUtil.getConnection();
		 
		   try {
			   //创建stmt对象
			pstmt=con.prepareStatement(sql);
			//设置占位符
			if(paramsValue !=null&& paramsValue.length>0){
				for(int i=1;i<=paramsValue.length;i++){
					pstmt.setObject(i, paramsValue[i-1]);
				}
			}
			//执行查询
			rs=pstmt.executeQuery();
			//获得结果元数据
			ResultSetMetaData rsmd= rs.getMetaData();
			//获得返回的列数
			int count=rsmd.getColumnCount();
			//将获得的值存到实体
			while(rs.next())
			{
				T t=clazz.newInstance();
				for(int i=1;i<=count;i++)
				{
					//通过元数据获得列植
					String name=rsmd.getColumnName(i);
					Object value=rs.getObject(name);
					BeanUtils.setProperty(t, name, value);
				}
				//添加到list
				list.add(t);
			}
				
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(con, pstmt,rs);
		}
		
	}
}
