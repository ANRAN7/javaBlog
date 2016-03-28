package com.rlovep.proxy._static;

public class App {
    public static void main(String[] args) {
		IUserDao userDao=new UserDao();
		UserDaoProxy userProxy=new UserDaoProxy(userDao);
		userProxy.save();
	}
}
