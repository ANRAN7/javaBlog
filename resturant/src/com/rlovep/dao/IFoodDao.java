package com.rlovep.dao;

import java.util.List;

import com.rlovep.entity.Food;
import com.rlovep.utils.PageBean;



/**
 * 
* @ClassName: IFoodDao
* @Description: 菜品类处理类
* @author peace w_peace@163.com 
* @date 14 Dec 2015 3:45:35 pm
*
 */
public interface IFoodDao {
	void add(Food food);
	
	void delete(int id);
	
	void updata(Food food);
	
	List<Food> query();

	Food findById(int id);

	List<Food> query(String keyword);
	
	List<Food> findByType(int type);
	/**
	 * 分页查询数据
	 */
	void getAll(PageBean<Food> pb);
	
	
	/**
	 * 查询总记录数
	 */

	int getTotalCount(PageBean<Food> pb);
}
