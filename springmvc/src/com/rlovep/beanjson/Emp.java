package com.rlovep.beanjson;

import java.util.Date;

public class Emp {
	private Integer id;
	private String username;
	private Double salary;
	private Date hiredate;
	public Emp(){}
	public Emp(Integer id, String username, Double salary, Date hiredate) {
		this.id = id;
		this.username = username;
		this.salary = salary;
		this.hiredate = hiredate;
	}
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
}
