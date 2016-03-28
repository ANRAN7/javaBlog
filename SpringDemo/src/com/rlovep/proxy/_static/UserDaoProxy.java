package com.rlovep.proxy._static;

public class UserDaoProxy implements IUserDao{
    IUserDao userDao;
    
	public UserDaoProxy(IUserDao userDao) {
		this.userDao=userDao;
	}

	@Override
	public void save() {
		System.out.println("使用代理执行");
		userDao.save();
	}
     
}
