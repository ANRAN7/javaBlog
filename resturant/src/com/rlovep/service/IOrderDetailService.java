package com.rlovep.service;

import java.util.List;

import com.rlovep.entity.OrderDetail;


public interface IOrderDetailService {

	void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderId(int id);
}
