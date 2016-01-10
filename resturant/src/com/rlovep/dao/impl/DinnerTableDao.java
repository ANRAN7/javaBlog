package com.rlovep.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rlovep.dao.IDinnerTableDao;
import com.rlovep.entity.DinnerTable;

import com.rlovep.utils.JdbcUtils;

public class DinnerTableDao implements IDinnerTableDao{
    private QueryRunner qr=JdbcUtils.getQuerrRunner();
    static Log log=LogFactory.getLog(DinnerTable.class);
    //增加餐桌
	@Override
	public void add(DinnerTable dt) {
      	try {
			String sql="Insert dinnerTable(tableName) values(?)";
			qr.update(sql, dt.getTableName());
		} catch (SQLException e) {
			log.error("添加餐桌错误");
			throw new RuntimeException(e);
		}
	}
    //删除餐桌
	@Override
	public void delete(int id) {
		try {
			String sql="delete from dinnerTable where id=?";
			qr.update(sql, id);
		} catch (SQLException e) {
			log.error("删除餐桌错误");
			throw new RuntimeException(e);
		}	
	}

	@Override
	public void update(DinnerTable dt) {
		try {
			String sql="update dinnerTable set tableStatus=?,orderDate=? where id=?";
			qr.update(sql,dt.getTableStatus(),dt.getOrderDate(),dt.getId());
		} catch (SQLException e) {
			log.error("更新餐桌错误");
			throw new RuntimeException(e);
		}	
	}

	@Override
	public List<DinnerTable> query() {
		try {
			String sql="select * from dinnerTable";
		     List<DinnerTable> query = qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
			return query;
		} catch (SQLException e) {
			log.error("查询餐桌错误");
			throw new RuntimeException(e);
		}	
	}

	@Override
	public DinnerTable finndByid(int id) {
		try {
			String sql="select * from dinnerTable where id=?";
			DinnerTable query = qr.query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
			return query;
		} catch (SQLException e) {
			log.error("查询全部餐桌错误");
			throw new RuntimeException(e);
		}	
	}

	@Override
	public List<DinnerTable> query(String keywoord) {
		try {
			String sql="select * from dinnerTable where tableName Like ?";
		     List<DinnerTable> query = qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class),"%"+keywoord +"%");
			return query;
		} catch (SQLException e) {
			log.error("搜索餐桌错误");
			throw new RuntimeException(e);
		}	
	}

	@Override
	public void quitTabe(int id) {
		String sql = "UPDATE dinnerTable SET tableStatus=?,orderDate=? WHERE id=?";
		
		try {
			qr.update(sql,0,null,id);
		} catch (Exception e) {
			log.error("退出餐桌错误");
			throw new RuntimeException(e);
		}
		
	}
}
