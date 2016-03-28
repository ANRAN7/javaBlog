package com.rlovep.service;

import com.rlovep.dao.DeptDao;
import com.rlovep.entity.Dept;

public class DeptService {
	private DeptDao dao=new DeptDao();
     //获得部门通过id
	public Dept getDeptById(int id){
		return dao.getDeptById(id);
	}
}
