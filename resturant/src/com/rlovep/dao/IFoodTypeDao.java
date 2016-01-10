package com.rlovep.dao;

import java.util.List;

import com.rlovep.entity.FoodType;
/**
 * 
* @ClassName: IFoodTypeDao
* @Description: 菜系处理接口
* @author peace w_peace@163.com 
* @date 14 Dec 2015 3:46:43 pm
*
 */
public interface IFoodTypeDao {
void add(FoodType foodtype);
	
	void delete(int id);
	
	void updata(FoodType foodtype);
	
	List<FoodType> query();

	FoodType findById(int id);

	List<FoodType> query(String keyword);
	
	Integer getFirstType();
}
