package com.rlovep.shiwu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

import com.rlovep.jdbc.*;
/**创建表的sql语句
 * create table if not exists account(
				id int primary key auto_increment,
                accountName varchar(10) not null,
                money int 
);
insert into account values(null,'peace',10000);
 */
/**
 * 
* @ClassName: AccountDao
* @Description: 测试事务逻辑
* @author peace w_peace@163.com 
* @date 9 Nov 2015 10:18:22 pm
*
 */
public class AccountDao {
	private Connection connection;
	private PreparedStatement statement;
	/**
	 * 
	* @Title: trans1 
	* @Description: 没有使用事务时，如果在一次连接中执行很多语句时有一条语句出错，前面的会正常执行。 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
   public void trans1(){
	   //正确的语句转出100元
	   String sql_zhuan="update account set money=money-100 where accountName='peace';";
	   //语句错误，没有回滚的话，转的钱就不见了。UPDATE1多了一个1
	   String sql_ru = "UPDATE1 account SET money=money+100 WHERE accountName='rong';";
	   try {
		   connection = JdbcUtil.getConnection(); // 默认开启的隐士事务
		   connection.setAutoCommit(true);//设置为自动提交

			/*** 第一次执行SQL ***/
			statement = connection.prepareStatement(sql_zhuan);
			statement.executeUpdate();

			/*** 第二次执行SQL ***/
			statement = connection.prepareStatement(sql_ru);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(11);
		} finally {
			JdbcUtil.close(connection, statement, null);
		}
   }
   //带有事务回滚的提交方式，当有语句错误时，回滚到做开始的位置
   public void trans2(){
	   String sql_zhuan="update account set money=money-100 where accountName='peace';";
	 //语句错误，没有回滚的话，转的钱就不见了。UPDATE1多了一个1
	   String sql_ru = "UPDATE1 account SET money=money+100 WHERE accountName='rong';";
	   try {
		   connection = JdbcUtil.getConnection(); // 默认开启的隐士事务
		   connection.setAutoCommit(false);// 一、设置事务为手动提交
         
			/*** 第一次执行SQL ***/
			statement = connection.prepareStatement(sql_zhuan);
			statement.executeUpdate();

			/*** 第二次执行SQL ***/
			statement = connection.prepareStatement(sql_ru);
			statement.executeUpdate();

		} catch (Exception e) {
			// 二、 出现异常，需要回滚事务
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JdbcUtil.close(connection, statement, null);
		}
   }
   
   //回滚到特定位置
   public void trans3(){
	   String sql_zhuan="update account set money=money-100 where accountName='peace';";
	 //假设这条语句错误，没有回滚的话，转的钱就不见了。
	   String sql_ru = "update account SET money=money+100 WHERE accountName='rong';";
	  //第二次转账语句有错
	   String sql_zhuan2="update account set money=money-100 where accountName='peace';";
		 //假设这条语句错误，没有回滚的话，转的钱就不见了。
		   String sql_ru2 = "update1 account SET money=money+100 WHERE accountName='rong';";
		   //定义标记
		   Savepoint setSavepoint=null;
	   try {
		   connection = JdbcUtil.getConnection(); // 默认开启的隐士事务
		   connection.setAutoCommit(false);// 一、设置事务为手动提交
           //第一次转账
			/*** 第一次执行SQL ***/
			statement = connection.prepareStatement(sql_zhuan);
			statement.executeUpdate();
			/*** 第二次执行SQL ***/
			statement = connection.prepareStatement(sql_ru);
			statement.executeUpdate();
          //设置回滚到此处的标志点
			setSavepoint = connection.setSavepoint();
			//第二次转账
			/*** 第一次执行SQL ***/
			statement = connection.prepareStatement(sql_zhuan2);
			statement.executeUpdate();
			/*** 第二次执行SQL ***/
			statement = connection.prepareStatement(sql_ru2);
			statement.executeUpdate();
		} catch (Exception e) {
			// 二、 出现异常，需要回滚事务
			try {
				connection.rollback(setSavepoint);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JdbcUtil.close(connection, statement, null);
		}
   }
   //测试用例
   @Test
	public void test(){
	   //执行之后，会看到peace少了100元 。
		//this.trans1();
		//执行之后，会看到peace没有少 。
		//this.trans2();
		//执行之后，会看到第一次转账成功，第二次转账没有执行 。
		this.trans3();
	}
   
}
