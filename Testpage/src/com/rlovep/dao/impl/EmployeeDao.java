package com.rlovep.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.rlovep.dao.IEmployeeDao;
import com.rlovep.entity.Employee;
import com.rlovep.entity.PageBean;
import com.rlovep.utile.JdbcUtiles;

public class EmployeeDao implements IEmployeeDao{

	@Override
	public void getPage(PageBean<Employee> page) {
		//设置总行数
		int allRow=getAllRow();
		page.setAllRow(allRow);
		/*
		 * 问题： jsp页面，如果当前页为首页，再点击上一页报错！
		 *              如果当前页为末页，再点下一页显示有问题！
		 * 解决：
		 * 	   1. 如果当前页 <= 0;       当前页设置当前页为1;
		 * 	   2. 如果当前页 > 最大页数；  当前页设置为最大页数
		 */
		int curpage=page.getCurpage();
		//1. 如果当前页 <= 0;       当前页设置当前页为1;
		if(curpage<=0)page.setCurpage(1);
		// 2. 如果当前页 > 最大页数；  当前页设置为最大页数
		if(curpage>page.getAllPage())page.setCurpage(page.getAllPage());
		//计算查询的起始行、返回的行数
		int index=(page.getCurpage()-1)*page.getPerRow();
		int count=page.getPerRow();
		
		try {
			//分页查询
			String sql="select * from employee limit ?,?";
			//得到QueryRunner对象
			QueryRunner qr=JdbcUtiles.getQureyRunner();
			//将查询的结果封装到list
			List<Employee> list = qr.query(sql, new BeanListHandler<>(Employee.class),index,count);
			//设置page对象
			page.setList(list);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getAllRow() {
		//使用count进行计数
		String sql="select count(*) from employee";
		//获得QueryRunner 对象
		QueryRunner qr = JdbcUtiles.getQureyRunner();
		try {
		     //获得总数
			Long count=qr.query( sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
