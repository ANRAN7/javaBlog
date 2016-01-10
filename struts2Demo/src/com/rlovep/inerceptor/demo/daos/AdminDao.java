package com.rlovep.inerceptor.demo.daos;

import java.sql.SQLException;
import java.util.List;


import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.rlovep.inerceptor.demo.utils.Admin;
import com.rlovep.inerceptor.demo.utils.JdbcUtils;

public class AdminDao {
       public boolean login(Admin admin)
       {
    	   boolean flag=false;
    	   String sql="select * from admin where username=? and pwd=?";
    	   try {
			Admin query = JdbcUtils.getQuerrRunner().query(sql, new BeanHandler<Admin>(Admin.class),admin.getUsername(),admin.getPwd() );
			System.out.println(query);
			if(query !=null)
				flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   return flag;
       }
       public List<Admin> getAll(){
   		String sql = "select * from admin ";
   		try {
   			return JdbcUtils.getQuerrRunner()
   				.query(
   					sql, 
   					new BeanListHandler<Admin>(Admin.class));
   		} catch (SQLException e) {
   			throw new RuntimeException(e);
   		}
   	}
       /*public static void main(String[] args) {
		List<Admin> list = new AdminDao().getAll();
		System.out.println(list.get(0).getUsername());
	}*/
}
