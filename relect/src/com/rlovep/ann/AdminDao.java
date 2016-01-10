package com.rlovep.ann;

public class AdminDao extends BaseDao<Admin> {
	public static void main(String[] args) {
		AdminDao adminDao=new AdminDao();
		System.out.println(adminDao.findById(3));
		System.out.println(adminDao.getAll());
	}
}
