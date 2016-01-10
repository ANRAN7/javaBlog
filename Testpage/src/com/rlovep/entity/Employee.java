package com.rlovep.entity;
/**
 * 
* @ClassName: Employee
* @Description: 数据实体
* @author peace w_peace@163.com 
* @date 16 Nov 2015 12:07:41 pm
*
 */
public class Employee {
      private int id;
      private String empName;
      private int deptId;
      private int bossid;
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
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public int getBossid() {
		return bossid;
	}
	public void setBossid(int bossid) {
		this.bossid = bossid;
	}
}
