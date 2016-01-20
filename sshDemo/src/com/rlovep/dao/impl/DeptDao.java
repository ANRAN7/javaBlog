package com.rlovep.dao.impl;

import com.rlovep.dao.IDepDao;
import com.rlovep.entity.Dept;
//只需要继承通用操作，和特点接口就行：这里接口中没有方法，可以加方法
public class DeptDao extends BaseDao<Dept> implements IDepDao{

}
