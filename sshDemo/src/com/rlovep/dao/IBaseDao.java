package com.rlovep.dao;

import java.util.List;
/*
 * * 所有dao的通用操作接口定义
 */
public interface IBaseDao<T> {
	/**
	 * 保存
	 * @param obj
	 */
	void save(T obj);

	/**
	 * 跟新对象信息
	 * @param obj
	 */
	void update(T obj);

	/**
	 * 根据主键删除
	 * @param id
	 */
	void delete(int id);

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	T findById(int id);

	/**
	 * 查询全部
	 * @return
	 */
	List<T> getAll();

}
