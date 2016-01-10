package com.rlovep.service.impl;

import com.rlovep.dao.IEmployeeDao;
import com.rlovep.dao.impl.EmployeeDao;
import com.rlovep.entity.Employee;
import com.rlovep.entity.PageBean;
import com.rlovep.service.IEmployeeService;

/**
 * 
* @ClassName: EmployeeService
* @Description: 业务逻辑层实现
* @author peace w_peace@163.com 
* @date 16 Nov 2015 2:07:21 pm
*
 */
public class EmployeeService implements IEmployeeService{
	//创建dao实例
    private IEmployeeDao emploeedao=new EmployeeDao();
    //实现接口方法
	@Override
	public void getPage(PageBean<Employee> page) {
		
		try {
			emploeedao.getPage(page);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
