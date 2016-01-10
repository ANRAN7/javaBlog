package com.rlovep.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.rlovep.jdbc.JdbcUtil;

/**创建表的sql语句：
 * create table if not exists admin(
				sid int primary key auto_increment,
                username varchar(10) not null,
                pwd varchar(6) default '123456'
);
 */
/**
* @ClassName: AdminDao
* @Description: 批处理sql语句
* @author peace w_peace@163.com 
* @date 9 Nov 2015 4:59:27 pm
*
 */
public class AdminDao {
	//定义成员变量
    private Connection connection;
    private PreparedStatement statement;
    //批量执行sql语句：使用batch：传入参数为list
	public void save(List<Admin>list){
		//使用预编译操作
         String sql="insert into admin(username,pwd) values(?,?)";
          try {
        	connection = JdbcUtil.getConnection();
			statement=connection.prepareStatement(sql);
            int i=0;
            //遍历链表
			for(Admin a:list){
				//设置位置参数
               statement.setString(1, a.getUserName());
               statement.setString(2, a.getPwd());
               //添加批处理
               statement.addBatch();
               //每六次执行一次批处理
               if(i++==5)
               {
            	   i=0;
            	   //执行批处理：获得的数组为每次执行的操作影像的行数，按添加顺序获得
            	  int count[]= statement.executeBatch();
            	  //清楚这次执行完的批处理
            	   statement.clearBatch();
            	   //打印出总共执行了多少条
            	  System.out.println("总共执行了这么多行"+count.length);
               }
            }
			//每超过六次的执行
			 int count[]=statement.executeBatch();
			System.out.println("总共执行了这么多行"+count.length);
          } catch (SQLException e) {
			e.printStackTrace();
		}finally
          {
			JdbcUtil.close(connection, statement);
          }
          
    }
	//进行测试
	@Test
	public void testBatch()
	{
		List<Admin> list=new ArrayList<>();
		for(int i=0;i<10;i++){
			Admin admin=new Admin();
			admin.setUserName("peace"+i);
			admin.setPwd("1234"+i);
			list.add(admin);
		}
		this.save(list);
	}
}
