package com.rlovep.dao;

import java.util.List;

import com.rlovep.entity.Orders;
import com.rlovep.utils.PageBean;

/**
 * 
* @ClassName: IOrdersDao
* @Description:订单处理接口 
* @author peace w_peace@163.com 
* @date 14 Dec 2015 3:46:10 pm
*
 */
public interface IOrdersDao {

void update(Orders orders);
	
	List<Orders> query();

	void add(Orders orders);
	
	int getCount();
    //待写
	void getAll(PageBean<Orders> pb);

	int getTotalCount();
}
