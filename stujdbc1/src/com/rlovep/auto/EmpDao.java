package com.rlovep.auto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.rlovep.jdbc.JdbcUtil;

/*创建表的sql语句
 * create table if not exists ddept(
				did int primary key auto_increment,
                deptName varchar(10) not null,
                index(did)
)engine=innodb character set utf8 collate utf8_general_ci auto_increment=1;#设置引擎以及字符集
create table if not exists employ(
				eid int ,
                empName varchar(10) not null,
                index(eid),
                foreign key(eid) references ddept(did) on delete cascade on update cascade#建立外键，
)engine=innodb character set utf8 collate utf8_general_ci auto_increment=1;#设置引擎以及字符集
 */

/**
 * 
* @ClassName: EmpDao
* @Description: 测试关联插入和自动增长
* @author peace w_peace@163.com 
* @date 9 Nov 2015 9:05:12 pm
*
 */
public class EmpDao {
	//插入ddept表的sql语句
    private String sql_insert="insert into ddept(deptName) values(?)";
    //查询是否有对应名字的ddept
    private String sql_qul="select did from ddept where deptName=?";
    //插入employ的语句
    private String sql_insert_e="insert into employ(empName,eid) values(?,?)";
    /**
     * 
    * @Title: guanlian 
    * @Description:进行关联插入测试，包括获得自动增长的位置
    * @param employ 
    * @return:void   
    * @throws 
    * @author peace w_peace@163.com
     */
    public void guanlian(Employ employ){
    	
    	Connection connection=null;
    	ResultSet rs=null;
    	PreparedStatement statement=null;
    	//保存dpid
    	int depId=0;
    	//获得ddept的名字
    	String dName=employ.getDept().getDeptName();
    	try {
    		//获得连接
			connection=JdbcUtil.getConnection();
			//查询对应名字是否存在
			statement=connection.prepareStatement(sql_qul);
			//给参数填值
			statement.setString(1, dName);
			rs=statement.executeQuery();
			//如果名字存在则获得id，不存在则插入ddept表
			if(rs.next()){
				
				depId=rs.getInt("did");
			}
			else{
				//插入，第一个参数为sql，第二个参数为需要返回自动增长建值
				statement=connection.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS);
				//给参数填值并执行
				statement.setString(1, dName);
				statement.executeUpdate();
				//获得自动增长键值
				rs=statement.getGeneratedKeys();
				if(rs.next()){
					//为第一个参数
					depId=rs.getInt(1);
				}
			}
			//保存员工，employ
			statement=connection.prepareStatement(sql_insert_e);
			//第一个参数为员工名字
			statement.setString(1, employ.getEname());
			//第二个参数为关联ddept的id
			statement.setInt(2, depId);
			//执行
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally {
			JdbcUtil.close(connection, statement, rs);
		}
    	
    }
    //测试用例
    @Test
    public void testAuto(){
    	Dept dept=new Dept();
    	dept.setDeptName("应用开发部");
    	Employ emp = new Employ();
		emp.setEname("王和平");
		emp.setDept(dept);   // 关联
		// 调用dao保存
		EmpDao empDao = new EmpDao();
		empDao.guanlian(emp);
    }
}
