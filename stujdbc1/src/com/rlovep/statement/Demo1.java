package com.rlovep.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.rlovep.jdbc.JdbcUtil;
/*
 * DML（data manipulation language）是数据操纵语言：它们是SELECT、UPDATE、INSERT、DELETE，就象它的名字一样，这4条命令是用来对数据库里的数据进行操作的语言。
  DDL（data definition language）是数据定义语言：DDL比DML要多，主要的命令有CREATE、ALTER、DROP等，DDL主要是用在定义或改变表（TABLE）的结构，数据类型，表之间的链接和约束等初始化工作上，他们大多在建立表时使用。
 */

/**
 * 
* @ClassName: Demo1
* @Description:使用statement执行sql静态语句
* @author peace w_peace@163.com 
* @date 8 Nov 2015 3:07:30 pm
*
 */
public class Demo1 {
	/**
	 * 
	* @Title: testDDl 
	* @Description: 执行ddl语句：创建表和alter语句
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */

    @Test
	public void testDDl(){
    	
    	Connection con=null;//连接
    	Statement statement=null;//statement类 未预编译sql，容易注入错误
    	con=JdbcUtil.getConnection();//获得连接
    	ResultSet rs=null;//封装查询出来的数据
    	try {
    		//通连接过得声明类
    		statement=con.createStatement();
			//创建表的sql语句
			String sql="create table if not exists stu("//表不存在则创建
					          + "sid int primary key auto_increment,"//设置为主键和自动增长
					          + "sname varchar(20) not null,"//不为空
					          + "sgender varchar(4) default '女')"//默认值为女
					          + "engine=innodb charset utf8 collate utf8_general_ci ";//设置字符集
           
			int count = statement.executeUpdate(sql);//执行ddl语句，返回影响的行数
			System.out.println("影响了"+count+"行");
			//插入几条数据用于测试：此处语句为dml
			sql="insert into stu(sname) values('peace1')";
			count = statement.executeUpdate(sql);
			System.out.println("影响了"+count+"行");
			//第二条数据
			sql="insert into stu values(null,'peace2','男')";
			count = statement.executeUpdate(sql);
			System.out.println("影响了"+count+"行");
			//第三条数据
			sql="insert into stu values(null,'peace3','男')";
			count = statement.executeUpdate(sql);
			System.out.println("影响了"+count+"行");
			//获得刚插如的数据；
			sql = "SELECT * FROM stu";
			//执行sql
			 rs= statement.executeQuery(sql);//执行select语句，返回查询结果；
			//遍历结果
			while(rs.next()){
				int id = rs.getInt("sid");//通过列名获得数据，可以通过序号获得：比如sid为1：则 id = rs.getInt("1");
				String name = rs.getString("sname");
				String gender = rs.getString("sgender");
				System.out.println(id+","+name+","+gender);//打印
			}
			//修改属性  alter table stu modify column sname varchar(20) default 'peace';
			sql="alter table stu modify column sname varchar(20) default 'peace'";
			count = statement.executeUpdate(sql);
			System.out.println("影响了"+count+"行");
			//添加属性alter table stu add teach_id int;
			sql="alter table stu add teach_id int";
			count = statement.executeUpdate(sql);
			System.out.println("影响了"+count+"行");
			//删除属性alter table stu drop column teach_id 
			sql="alter table stu drop column teach_id";
			count = statement.executeUpdate(sql);
			System.out.println("影响了"+count+"行");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//关闭连接
	    	JdbcUtil.close(con, statement,rs);
		}
    	
	}
    /**
     * 
    * @Title: testDml 
    * @Description:执行dml语句。增加，修改，删除。
    * @return:void   
    * @throws 
    * @author peace w_peace@163.com
     */
    @Test
    public void testDml(){
    	Connection con=null;
    	Statement statement=null;
    	con=JdbcUtil.getConnection();//获得连接
    	try {
    		//创建statement 
			statement = con.createStatement();
			//添加
			String sql="insert into stu(sname) values('rong')";
			int count=statement.executeUpdate(sql);
			System.out.println("影响了"+count+"行");
			//修改
		     sql="update stu set sname='王和平' where sid=1 or sid=2";
			  count=statement.executeUpdate(sql);
			System.out.println("影响了"+count+"行");
			//删除
			 sql="delete from stu where sid in (5,6,7)";
			  count=statement.executeUpdate(sql);
			System.out.println("影响了"+count+"行");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	JdbcUtil.close(con, statement);
    }
    /**
     * 
    * @Title: testDql 
    * @Description: 测试dml语句 select
    * @return:void   
    * @throws 
    * @author peace w_peace@163.com
     */
    @Test
    public void testDql(){
    	Connection con=null;
    	Statement statement=null;
    	ResultSet rs=null;
    	con=JdbcUtil.getConnection();
    	try {
			statement=con.createStatement();
			String sql = "SELECT * FROM stu";
			//执行sql
			rs = statement.executeQuery(sql);
			//遍历结果
			while(rs.next()){
				int id = rs.getInt("sid");
				String name = rs.getString("sname");
				String gender = rs.getString("sgender");
				System.out.println(id+","+name+","+gender);
   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	JdbcUtil.close(con, statement,rs);
    }
}
