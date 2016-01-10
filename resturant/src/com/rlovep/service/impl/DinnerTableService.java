package com.rlovep.service.impl;

import java.util.Date;
import java.util.List;

import com.rlovep.dao.IDinnerTableDao;
import com.rlovep.entity.DinnerTable;
import com.rlovep.service.IDinnerTableService;
import com.rlovep.utils.BeanFactory;

public class DinnerTableService implements IDinnerTableService{
    IDinnerTableDao dao=BeanFactory.getInstance("dinnerTableDao", IDinnerTableDao.class);
	@Override
	public void add(DinnerTable dt) {
		// TODO Auto-generated method stub
		dao.add(dt);
	}

	@Override
	public void delete(int id) {
        dao.delete(id);		
	}

	@Override
	public void updata(DinnerTable dt) {
		dao.update(dt);
	}

	@Override
	public List<DinnerTable> query() {
		return dao.query();
	}

	@Override
	public DinnerTable findById(int id) {

		return dao.finndByid(id);
	}

	@Override
	public List<DinnerTable> query(String keyword) {
		return dao.query(keyword);
	}

	@Override
	public DinnerTable changeState(int id) {
	 DinnerTable table = dao.finndByid(id);
	 int status=table.getTableStatus();
	 if(status==0)
	 {
		 status=1;
		 Date date=new Date();
		 table.setOrderDate(date);
	 }
	 else if(status==1){
		 status=0;
		 table.setOrderDate(null);
	 }
	 table.setTableStatus(status);
	 this.updata(table);
	 return table;
	}

	@Override
	public void quitTable(int id) {
		dao.quitTabe(id);
		
	}

}
