package com.rlovep.entity;

import java.util.LinkedHashSet;
import java.util.Set;

public class Dept {

	private int id;
	private String name;
	private Set<Employee> emps=new LinkedHashSet<>();
	public Set<Employee> getEmps() {
		return emps;
	}
	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
}
