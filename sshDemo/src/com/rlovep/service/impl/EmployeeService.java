package com.rlovep.service.impl;

import java.util.List;

import com.rlovep.dao.impl.EmployeeDao;
import com.rlovep.entity.Employee;
import com.rlovep.service.IEmployeeService;

public class EmployeeService implements IEmployeeService{
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public void save(Employee emp) {
		employeeDao.save(emp);
		
	}

	@Override
	public void update(Employee emp) {
	   employeeDao.update(emp);
		
	}

	@Override
	public Employee findById(int id) {
		
		return employeeDao.findById(id);
	}

	@Override
	public List<Employee> getAll() {
	
		return employeeDao.getAll();
	}

	@Override
	public List<Employee> getAll(String employeeName) {
		
		return null;
	}

	@Override
	public void delete(int id) {
		
		employeeDao.delete(id);
	}

	@Override
	public void deleteMany(int[] ids) {
		if (ids != null && ids.length >0) {
	   for(int id:ids){
		   employeeDao.delete(id);
	   }
		}
	}
    
}
