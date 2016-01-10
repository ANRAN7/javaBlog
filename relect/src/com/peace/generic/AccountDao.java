package com.peace.generic;
//测试用的类，主要为了传入泛型参数Account
public class AccountDao extends BaseDao<account>{
     public static void main(String[] args) {
		AccountDao accountDao=new AccountDao();
		System.out.println(accountDao.getAll());
	}
}
