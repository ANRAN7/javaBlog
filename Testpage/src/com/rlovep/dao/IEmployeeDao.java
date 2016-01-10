package com.rlovep.dao;

import com.rlovep.entity.Employee;
import com.rlovep.entity.PageBean;

/**
 * 
* @ClassName: IEmployeeDao
* @Description: 数据库操作接口
* @author peace w_peace@163.com 
* @date 16 Nov 2015 12:10:41 pm
*
 */
public interface IEmployeeDao {
    /**
     * 
    * @Title: getPage 
    * @Description: 获得一页
    * @param page 
    * @return:void   
    * @throws 
    * @author peace w_peace@163.com
     */
	public void getPage(PageBean<Employee> page);
	/**
	 * 
	* @Title: getAllRow 
	* @Description: 获得总记录行数
	* @return 
	* @return:int   
	* @throws 
	* @author peace w_peace@163.com
	 */
    public int getAllRow();
}
