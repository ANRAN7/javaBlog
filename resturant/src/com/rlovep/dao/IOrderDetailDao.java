package com.rlovep.dao;

import java.util.List;

import com.rlovep.entity.OrderDetail;

/**
 * 
* @ClassName: IOrderDetailDao
* @Description: 订单详细接口
* @author peace w_peace@163.com 
* @date 14 Dec 2015 3:47:25 pm
*
 */
public interface IOrderDetailDao {
void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderid(int id);
}
