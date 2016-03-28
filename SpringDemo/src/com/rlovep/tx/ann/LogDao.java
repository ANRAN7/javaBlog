package com.rlovep.tx.ann;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class LogDao {
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	// 始终开启事务
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insertLog() {
		jdbcTemplate.update("insert into Dept(deptName) values('在保存Dept..')");
	}
}
