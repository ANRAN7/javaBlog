package com.rlovep.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.rlovep.dao.IAdminDao;
import com.rlovep.entity.Admin;

public class AdminDao implements IAdminDao{
	// IOC容器(依赖)注入SessionFactory对象
    private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(Admin admin) {
		sessionFactory.getCurrentSession().save(admin);
	}
	@Override
	public Admin findByAdmin(Admin admin) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Admin where adminName=? and pwd=?");
		query.setParameter(0, admin.getAdminName());
		query.setParameter(1, admin.getPwd());
		return(Admin)query.uniqueResult();
	}
	
}
