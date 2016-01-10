package com.rlovep.inerceptor.demo.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rlovep.inerceptor.demo.daos.AdminDao;
import com.rlovep.inerceptor.demo.utils.Admin;

public class UserAction extends ActionSupport{
	private Admin admin;
    //数据库操作工具
	private AdminDao adminDao=new AdminDao();
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String login(){
		if(admin==null)
			return "input";
		System.out.println(admin);
		boolean status = adminDao.login(admin);
		System.out.println(status);
		if(status==false){
			return "input";
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("userinfo", admin);
		return SUCCESS;
	}
	// 列表
		public String list() {
			try {
				// 查询全部
				List<Admin> list = adminDao.getAll();
				// 保存到request
				ActionContext.getContext().getContextMap().put("listAdmin", list);
				return "list";
			} catch (Exception e) {
				return ERROR;
			}
		}
		
		public String add() {
			return null;
		}
	/*	public static void main(String[] args) {
			String list = new UserAction().list();
			System.out.println(list);
		}*/
}
