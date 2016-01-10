package com.rlovep.bean;

//javaBean
public class Person {
	
	private int id;
	
	private String name;

	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	//无参数构造函数
	public Person(){}
	//获得Id属性
	public int getId() {
		return id;
	}
	//设置
	public void setId(int id) {
		this.id = id;
	}
	//get方法
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    //tostring不在要求之中；
	@Override
	public String toString() {
	
		return "id:"+ this.id+" name:"+ this.name;
	}

}
