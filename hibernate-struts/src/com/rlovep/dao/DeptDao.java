package com.rlovep.dao;



import com.rlovep.entity.Dept;
import com.rlovep.utils.HibernateUtil;

public class DeptDao {
      //返回部门
	public Dept getDeptById(int id){
		return (Dept)HibernateUtil.getsession().get(Dept.class, id);
	}
}
