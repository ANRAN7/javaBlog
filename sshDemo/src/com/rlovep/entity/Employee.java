package com.rlovep.entity;

public class Employee {

	private int id;
	private String empName;
	private double salary;
	private Dept dept;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Dept getDept() {
		return dept;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", empName=" + empName + ", salary=" + salary + "]";
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	
}
