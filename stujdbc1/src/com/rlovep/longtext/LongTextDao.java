package com.rlovep.longtext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.rlovep.jdbc.JdbcUtil;
/**
 * -- 测试大数据类型
 CREATE TABLE longtxt(
      id INT PRIMARY KEY AUTO_INCREMENT,
      content LONGTEXT,
      img LONGBLOB
 );
 */
/**
 * 
* @ClassName: LongTextDao
* @Description: 测试长文本
* @author peace w_peace@163.com 
* @date 10 Nov 2015 9:29:02 am
*
 */
public class LongTextDao {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet rs;
	
	@Test
	// 1. 保存大文本数据类型   ( 写longtext)：与其他的去吧不大。只是设置位置参数时使用字符输入流
	public void testWriteTxt(){
		
	    String sql="insert into longtxt(content) values(?)";
	    //创建字符输入流
		FileReader reader=null;
		try {
			
			connection=JdbcUtil.getConnection();
			statement=connection.prepareStatement(sql);
			//或得文件输入路径
			String path=LongTextDao.class.getResource("/peace.txt").getPath();
			//获得输入字符流
			reader=new FileReader(new File(path));
			//设置位置参数：型号为字符流
			statement.setCharacterStream(1, reader);
			//执行
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(reader!=null)
			{
				try {
					reader.close();
					reader=null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			JdbcUtil.close(connection, statement, rs);
		}
	}
	@Test
	// 2. 读取大文本数据类型   ( 读longtext) 读取可以用两种方法，一种直接使用返回的结果获得String类型。一种字符输入流
	public void testReadTxt(){
		String sql="select content from longtxt";
		Reader reader=null;
		try {
			connection=JdbcUtil.getConnection();
			statement=connection.prepareStatement(sql);
			rs=statement.executeQuery();
			while(rs.next())
			{
				//第一种读取 使用字符输入流
				reader=rs.getCharacterStream("content");
				BufferedReader bufferedReader=new BufferedReader(reader);
				System.out.println(bufferedReader.readLine());
				//第二中读取
				String peace=rs.getString("content");
				System.out.println(peace);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(reader!=null)
			{
				try {
					reader.close();
					reader=null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			JdbcUtil.close(connection, statement, rs);
		}
	}
	
}
