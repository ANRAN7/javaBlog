package com.rlovep.tx.xml;

public class DeptService {
	private DeptDao deptDao;

	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	public void save(Dept dept){
		//第一次调用
		deptDao.save(dept);
		//出现错误：应该回滚事务
		int i=1/0;
		deptDao.save(new Dept());
	}
  
}
