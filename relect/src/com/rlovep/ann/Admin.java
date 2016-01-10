package com.rlovep.ann;

import com.rlovep.ann.util.Column;
import com.rlovep.ann.util.Id;
import com.rlovep.ann.util.Table;

// Admin=a_admin
@Table(tableName="admin")
public class Admin {

	@Id
	@Column(columnName = "id")
	private int a_id;
	
	@Column(columnName = "username")
	private String  a_userName;
	
	@Column(columnName = "pwd")
	private String a_pwd;

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public String getA_userName() {
		return a_userName;
	}

	public void setA_userName(String a_userName) {
		this.a_userName = a_userName;
	}

	public String getA_pwd() {
		return a_pwd;
	}

	

	public void setA_pwd(String a_pwd) {
		this.a_pwd = a_pwd;
	}
	@Override
	public String toString() {
		return "Admin [a_id=" + a_id + ", a_userName=" + a_userName + ", a_pwd=" + a_pwd + "]";
	}
}
