package com.rlovep.listParam;

import java.util.ArrayList;
import java.util.List;



public class Bean {
	private List<Emp> empList = new ArrayList<Emp>();
	public Bean(){}
	public List<Emp> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Emp> empList) {
		this.empList = empList;
	}
}
