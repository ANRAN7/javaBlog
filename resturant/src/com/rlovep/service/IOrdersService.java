package com.rlovep.service;
import java.util.List;

import com.rlovep.entity.Orders;
import com.rlovep.utils.PageBean;

public interface IOrdersService {
	void update(Orders orders);

	List<Orders> query();

	void add(Orders orders);
	
	int getCount();
	
	public void getAll(PageBean<Orders> pb);
}
