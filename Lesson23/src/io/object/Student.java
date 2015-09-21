package io.object;

import java.io.Serializable;

/**
 * ���ϣ����һ������ͨ��ObjectOutputStreamд���ļ��У�����������ʵ��Serializable�ӿ�
 * @author Administrator
 *
 */
public class Student implements Serializable{
	private int id;
	private String username;
	//ֻҪһ������ͨ��transient����֮��������ԾͲ��ᱻ�洢
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
