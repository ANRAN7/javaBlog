package com.rlovep.contact.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.rlovep.contact.dao.ContactDao;
import com.rlovep.contact.entity.Contact;
import com.rlovep.contact.util.JdbcUtil;

/**创建表是sql语句
 * create table if not exists contact(
				sid int primary key auto_increment,
                sname varchar(20) not null,
                sgender varchar(4) default '女',
                sage int,
                sphone varchar(20),
                semail varchar(40),
                sqq varchar(20)
);
 */
/**
 * 
* @ClassName: ContactDaoImpl
* @Description: 数据库接口的具体实现：此去用数据库
* @author peace w_peace@163.com 
* @date 20 Oct 2015 4:02:41 pm
*
 */
public class ContactDaoImpl implements ContactDao  {

	/**
	 * 增加联系人
	 */
	@Override
	public void addContact(Contact contact) {
		Connection connection = null;
		PreparedStatement statement=null;
		try {
			//获得连接
			connection=JdbcUtil.getConnection();
			//插入数据的sql id使用自动增长
			String sql="INSERT INTO contact(sname,sgender,sage,sphone,semail,sqq) VALUES(?,?,?,?,?,?)";
			statement=connection.prepareStatement(sql);
			//设置位置参数
			statement.setString(1, contact.getName());
			statement.setString(2, contact.getGender());
			statement.setInt(3, contact.getAge());
			statement.setString(4, contact.getPhone());
			statement.setString(5, contact.getEmail());
			statement.setString(6, contact.getQq());
			//执行
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(connection, statement);
		}
		
	}
    /**
     * 更新contact标签
     */
	@Override
	public void updateContact(Contact contact) {
		Connection connection = null;
		PreparedStatement statement=null;
		try {
			connection=JdbcUtil.getConnection();
			//更新的sql语句
			String sql="update contact set sname=?,sgender=?,sage=?,sphone=?,semail=?,sqq=? where sid=?";
			statement=connection.prepareStatement(sql);
			//设置参数
			statement.setString(1, contact.getName());
			statement.setString(2, contact.getGender());
			statement.setInt(3, contact.getAge());
			statement.setString(4, contact.getPhone());
			statement.setString(5, contact.getEmail());
			statement.setString(6, contact.getQq());
			statement.setInt(7, Integer.parseInt(contact.getId()));
			//执行
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(connection, statement);
		}
	}
	/**
	 * 删除contact标签
	 */
	@Override
	public void deleteContact(String id) {
		Connection connection = null;
		PreparedStatement statement=null;
		try {
			connection=JdbcUtil.getConnection();
			//删除的sql语句
			String sql="delete from contact  where sid=?";
			statement=connection.prepareStatement(sql);
			//通过id删除
			statement.setInt(1, Integer.parseInt(id));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(connection, statement);
		}
	}
     /**
      * 列出所有contact标签
      */
	@Override
	public List<Contact> findAll() {
		//创建保存的list
		List<Contact> contacts=new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement=null;
		ResultSet rs=null;
		
		try {
			connection=JdbcUtil.getConnection();	
			//从数据库获得数据
			String sql="select * from contact";
			statement=connection.prepareStatement(sql);
			rs = statement.executeQuery();
			//对查询结果进行遍历
			while(rs.next()){
				Contact c=new Contact();
				String id=Integer.toString(rs.getInt("sid"));
				//保持到contact中
				c.setId(id);
				c.setName(rs.getString("sname"));
				c.setGender(rs.getString("sgender"));
				c.setAge(rs.getInt("sage"));
				c.setPhone(rs.getString("sphone"));
				c.setEmail(rs.getString("semail"));
				c.setQq(rs.getString("sqq"));
				contacts.add(c);
			}
			//返回结果：结果不要放到finally中；
			return contacts;
		} catch (SQLException e) {	
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(connection, statement,rs);
		}
		
	}
    /**
     * 寻找到特定id的标签
     * 
     */
	@Override
	public Contact findById(String id) {
		
		Connection connection = null;
		PreparedStatement statement=null;
		ResultSet rs=null;
		Contact c=null;
		try {
			connection=JdbcUtil.getConnection();	
			//通过id获得数据
			String sql="select * from contact where sid=?";
			statement=connection.prepareStatement(sql);
			//设置位置参数
			statement.setInt(1, Integer.parseInt(id));
			rs = statement.executeQuery();
			//得到查询结果
			if(rs.next()){
				c=new Contact();
				c.setId(""+rs.getInt("sid"));
				c.setName(rs.getString("sname"));
				c.setGender(rs.getString("sgender"));
				c.setAge(rs.getInt("sage"));
				c.setPhone(rs.getString("sphone"));
				c.setEmail(rs.getString("semail"));
				c.setQq(rs.getString("sqq"));
				
			}
			return c;
		} catch (SQLException e) {	
			e.printStackTrace();
         throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(connection, statement,rs);
		
		}
		
	}
	/**
	 * 调查是否重名的contact
	 */
	@Override
	public boolean checkContact(String name) {
		Connection connection = null;
		PreparedStatement statement=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			connection=JdbcUtil.getConnection();	
			//通过名字查找
			String sql="select * from contact where sname=?";
			
			statement=connection.prepareStatement(sql);
			statement.setString(1, name);
			rs = statement.executeQuery();
			//判断是存在
			if(rs.next()){
				flag=true;
			}
		return flag;
		} catch (SQLException e) {	
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {		
			JdbcUtil.close(connection, statement,rs);			
		}
		
	}
}
