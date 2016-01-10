package com.rlovep.compositekey;

public class User {
      //名字跟地址不会重复
	private Compositekeys keys;
	private int age;
	public Compositekeys getKeys() {
		return keys;
	}
	public void setKeys(Compositekeys keys) {
		this.keys = keys;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
