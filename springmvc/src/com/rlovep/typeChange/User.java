package com.rlovep.typeChange;

import java.util.Date;

public class User {

	private Integer id = 1;
	private String username;
	private Double salary;
	private Date hiredate;
	public User(){}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	@Override
	public String toString() {
		return this.id+":"+this.username+":"+this.salary+":"+this.hiredate.toLocaleString();
	}
}

