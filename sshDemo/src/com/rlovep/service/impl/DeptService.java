package com.rlovep.service.impl;

import java.util.List;

import com.rlovep.dao.impl.DeptDao;
import com.rlovep.entity.Dept;
import com.rlovep.service.IDeptService;

public class DeptService implements IDeptService{
    private DeptDao deptDao;

	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

	@Override
	public void save(Dept dept) {
		deptDao.save(dept);
		
	}

	@Override
	public void update(Dept dept) {
		deptDao.update(dept);
		
	}

	@Override
	public Dept findById(int id) {
		
		return deptDao.findById(id);
	}

	@Override
	public void delte(int id) {
	    deptDao.delete(id);
		
	}

	@Override
	public List<Dept> getAll() {
		
		return deptDao.getAll();
	}
    
}
