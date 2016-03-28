package com.rlovep.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;



public class DeptDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void save(){
		String sql="insert into Dept(deptName) values('peace'); ";
		jdbcTemplate.update(sql);
		
	}

	public Dept findById(int id) {
		String sql = "select * from Dept where deptId=?";
		List<Dept> list = jdbcTemplate.query(sql,new MyResult(), id);
		return (list!=null && list.size()>0) ? list.get(0) : null;
	}
	
	public List<Dept> getAll() {
		String sql = "select * from Dept";
		List<Dept> list = jdbcTemplate.query(sql, new MyResult());
		return list;
	}
	///将记录封装成对象
	class MyResult implements RowMapper<Dept>{

		@Override
		public Dept mapRow(ResultSet rs, int index) throws SQLException {
			Dept dept=new Dept();
			dept.setDeptId(rs.getInt("deptId"));
			dept.setDeptName(rs.getString("deptName"));
			return dept;
		}
		
	}
}
