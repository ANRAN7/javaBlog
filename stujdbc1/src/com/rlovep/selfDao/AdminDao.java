package com.rlovep.selfDao;

import java.util.List;

import org.junit.Test;



public class AdminDao extends BaseDao {
   //删除
	public void delete(int id){
		  String sql="delete from admin where sid=?;";
		  Object[] params={id};
		  update(sql, params);
	}
	//插入
	public void save(Admin admin)
	{
		String sql="insert into admin (username,pwd) values (?,?);";
		Object[] params={admin.getUserName(),admin.getPwd()};
		update(sql, params);
	}
	//查询全部
	public List<Admin> getAll(){
		String sql="select * from admin";
		List<Admin> list=query(sql, null, Admin.class);
		return list;
	}
	//根据id查询
	public Admin findByid(int id){
		String sql="select * from admin where id=?";
		List<Admin> list=query(sql, new Object[]{id}, Admin.class);
		return (list!=null&&list.size()>0)? list.get(0):null;
	}
	@Test
	public void test(){
		AdminDao adminDao = new AdminDao();
		//测试删除
		adminDao.delete(1);
		//测试插入
		Admin admin = new Admin();
		admin.setPwd("123");
		admin.setUserName("peace");
		adminDao.save(admin);
		// 测试查询
		List<Admin> list = adminDao.getAll();
		System.out.println(list);
	}
}
