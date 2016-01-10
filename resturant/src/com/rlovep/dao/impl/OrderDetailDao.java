package com.rlovep.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.rlovep.dao.IOrderDetailDao;
import com.rlovep.entity.OrderDetail;
import com.rlovep.utils.JdbcUtils;


public class OrderDetailDao implements IOrderDetailDao{
	private QueryRunner qr = JdbcUtils.getQuerrRunner();
	@Override
	public void add(OrderDetail od) {
		String sql =" INSERT orderDetail(orderId,food_id,foodCount) VALUES(?,?,?)";
		try {
			qr.update(sql,od.getOrderId(),od.getFood_id(),od.getFoodCount());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List<OrderDetail> query() {
		try {
			String sql ="SELECT * FROM orderDetail";
			return  qr.query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<OrderDetail> findByOrderid(int id) {
		try {
			String sql ="SELECT * FROM orderDetail where orderId=?";
			return  qr.query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
