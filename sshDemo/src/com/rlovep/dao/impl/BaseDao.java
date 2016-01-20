package com.rlovep.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import com.rlovep.dao.IBaseDao;
/*
 * 所有dao的通用操作，希望所有的dao都继承此类
 */
public class BaseDao<T> implements IBaseDao<T>{
	//当前操作实际的bean类型
	private Class<T>clazz;
	//获取类名称
	private String className;
	// IOC容器(依赖)注入SessionFactory对象
    private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	 public BaseDao() {
		Type type=this.getClass().getGenericSuperclass();
		//转换为参数化类型
		ParameterizedType pt=(ParameterizedType)type;// BaseDao<Employee>
		//得到实际类型
		Type types[]=pt.getActualTypeArguments();
		//获取实际类型
		clazz=(Class<T>)types[0];
		className = clazz.getSimpleName();//例如：Employee
	}
	@Override
	public void save(T obj) {
		sessionFactory.getCurrentSession().save(obj);
	}
	@Override
	public void update(T obj) {
		sessionFactory.getCurrentSession().update(obj);
		
	}
	@Override
	public void delete(int id) {
		sessionFactory.getCurrentSession().createQuery("delete from "+className+" where id=?").setInteger(0, id).executeUpdate();
	}
	@Override
	public T findById(int id) {
		
		return (T)sessionFactory.getCurrentSession().get(clazz, id);
	}
	@Override
	public List<T> getAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from "+className);
		List<T> list = query.list();
		return list;
	}
}
