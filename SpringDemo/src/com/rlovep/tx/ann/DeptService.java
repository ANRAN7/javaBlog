package com.rlovep.tx.ann;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptService {
	@Resource(name="deptDao")
	private DeptDao deptDao;
	@Resource(name="logDao")
    private LogDao logDao;
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	/*
	 *事务控制：
	 */
	@Transactional(
			 readOnly=false,//读写事务
			 timeout=-1,//事务的超时时间不限制
			 isolation=Isolation.DEFAULT,//事务的隔离级别，数据库有的默认
			 noRollbackFor=ArithmeticException.class, // 遇到数学异常不回滚
			 propagation=Propagation.REQUIRED//事务的传播行为
			
			)
	public void save(Dept dept){
		//第一次调用
		deptDao.save(dept);;
		logDao.insertLog();
		//出现错误：应该回滚事务
		int i=1/0;
		deptDao.save(dept);
	}
  
}
