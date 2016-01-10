package com.peace.generic;

public class AdminDao extends BaseDao<Admin> {

	
	public static void main(String[] args) {
		
		AdminDao adminDao=new AdminDao();
		/*Admin admin=adminDao.findByid(5);
		System.out.println(admin);*/
		System.out.println(adminDao.getAll());
	}
}
