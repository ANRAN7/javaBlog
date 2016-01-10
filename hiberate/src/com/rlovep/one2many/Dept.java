package com.rlovep.one2many;

import java.util.Set;

public class Dept {
     private int id;
     private String Dname;
     //一对多：一个部分可以有多个employee
     private Set<Employee> emps;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDname() {
		return Dname;
	}
	public void setDname(String dname) {
		Dname = dname;
	}
	public Set<Employee> getEmps() {
		return emps;
	}
	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}
     
}
