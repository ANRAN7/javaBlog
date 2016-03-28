package com.rlovep.action;

import com.opensymphony.xwork2.ActionContext;
import com.rlovep.entity.Dept;
import com.rlovep.service.DeptService;

public class DeptAction {
	private DeptService dService=new DeptService();
	private int id;
     public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String execute(){
		Dept dept = dService.getDeptById(id);
		ActionContext context = ActionContext.getContext();
		System.out.println(dept.getDeptName());
		ActionContext.getContext().getContextMap().put("dept", dept);
    	 //主键查询
    	 return "success";
     }
}
