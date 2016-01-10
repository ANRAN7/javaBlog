package com.rlovep.longtext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.rlovep.jdbc.JdbcUtil;

/**
 * 
* @ClassName: LongBolbDao
* @Description: 测试longbolb
* @author peace w_peace@163.com 
* @date 10 Nov 2015 10:08:50 am
*
 */
public class LongBolbDao {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet rs;
	@Test
	// 1. 二进制数据类型   ( 写longblob)
	public void testWriter(){
		String sql="insert into longtxt(img) values(?)";
		FileInputStream in=null;
		try {
			connection=JdbcUtil.getConnection();
			statement=connection.prepareStatement(sql);
			String path=LongTextDao.class.getResource("/1.png").getPath();
			in=new FileInputStream(new File(path));
			statement.setBinaryStream(1, in);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(in!=null)
			{
				try {
					in.close();
					in=null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			JdbcUtil.close(connection, statement, rs);
		}
	}
	@Test
	// 2. 读取大文本数据类型   ( 读longblob)
	public void testReader(){
		String sql="select img from longtxt where id=2";
		FileOutputStream out=null;
		InputStream in=null;
		try {
			connection=JdbcUtil.getConnection();
			statement=connection.prepareStatement(sql);
			rs=statement.executeQuery();
			while(rs.next()){
				 out=new FileOutputStream(new File("./src/2.png"));
				 in=rs.getBinaryStream("img");
				 int len=-1;
				 byte b[]=new byte[1024];
				 while((len=in.read(b))!=-1){
					 out.write(b, 0,len);
				 }
			   out.close();
			   in.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		finally{
			JdbcUtil.close(connection, statement, rs);
		}
	}

}
