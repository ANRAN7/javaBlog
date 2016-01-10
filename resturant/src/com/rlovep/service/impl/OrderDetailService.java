package com.rlovep.service.impl;

import java.util.List;

import com.rlovep.dao.IOrderDetailDao;
import com.rlovep.entity.OrderDetail;
import com.rlovep.service.IOrderDetailService;
import com.rlovep.utils.BeanFactory;


public class OrderDetailService implements IOrderDetailService{

	IOrderDetailDao dao = BeanFactory.getInstance("orderDetailDao", IOrderDetailDao.class);

	@Override
	public void add(OrderDetail od) {
		dao.add(od);
	}

	@Override
	public List<OrderDetail> query() {
		return dao.query();
	}
	@Override
	public List<OrderDetail> findByOrderId(int id) {
		return dao.findByOrderid(id);
	}
}
