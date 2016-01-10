package com.rlovep.service;

import com.rlovep.entity.Employee;
import com.rlovep.entity.PageBean;
/**
 * 
* @ClassName: IEmployeeService
* @Description: 业务逻辑层接口设计
* @author peace w_peace@163.com 
* @date 16 Nov 2015 2:05:54 pm
*
 */
public interface IEmployeeService {
      public void getPage(PageBean<Employee>page);
}
