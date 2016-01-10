package com.rlovep.service.impl;

import java.util.List;

import com.rlovep.dao.IFoodDao;
import com.rlovep.entity.Food;
import com.rlovep.service.IFoodService;
import com.rlovep.utils.BeanFactory;
import com.rlovep.utils.PageBean;



public class FoodService implements IFoodService{
	IFoodDao dao = BeanFactory.getInstance("foodDao", IFoodDao.class);
	
	@Override
	public void add(Food food) {
		dao.add(food);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
		
	}

	@Override
	public void updata(Food food) {
		dao.updata(food);
		
	}

	@Override
	public List<Food> query() {
		return dao.query();
	}

	@Override
	public Food findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Food> query(String keyword) {
		return dao.query(keyword);
	}
	
	@Override
	public void getAll(PageBean<Food> pb) {
		try {
			dao.getAll(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Food> findByType(int type) {
		return dao.findByType(type);
	}
}
