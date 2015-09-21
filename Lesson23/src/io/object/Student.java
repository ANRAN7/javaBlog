package io.object;

import java.io.Serializable;

/**
 * 如果希望把一个对象通过ObjectOutputStream写到文件中，这个对象必须实现Serializable接口
 * @author Administrator
 *
 */
public class Student implements Serializable{
	private int id;
	private String username;
	//只要一个属性通过transient设置之后这个属性就不会被存储
	private transient int moeny;
	
	public int getMoeny() {
		return moeny;
	}
	public void setMoeny(int moeny) {
		this.moeny = moeny;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Student(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	public Student() {
		super();
	}
	
	
}
