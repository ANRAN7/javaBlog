package com.rlovep.one2many;

import java.util.Set;

public class Dept {
     private int id;
     private String Dname;
     public Dept() {
		super();
	}
     //参数构造器
     public Dept(int id,String Dname) {
 		super();
 		this.id=id;
 		this.Dname=Dname;
 	}
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
	@Override
	public String toString() {
		return "Dept [id=" + id + ", Dname=" + Dname  + "]";
	}
	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}
     
}
