package com.rlovep.hello;
//工厂创建User对像
public class ObjectFactory {
     //通过实例方法
	public User getUser(){
		return new User(2,"工厂实例方法创建");
	}
	//通过静态方法
		public  static User getUseStatic(){
			return new User(3,"工厂静态方法创建");
		}
}
