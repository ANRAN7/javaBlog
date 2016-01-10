package com.rlovep.dao;

import java.util.List;

import com.rlovep.entity.DinnerTable;

/**
 * 
* @ClassName: IDinnerTableDao
* @Description: 餐桌处理接口
* @author peace w_peace@163.com 
* @date 14 Dec 2015 3:44:11 pm
*
 */
public interface IDinnerTableDao {
     void add(DinnerTable dt);
     void delete(int id);
     void update(DinnerTable dt);
     List<DinnerTable>query();
     DinnerTable finndByid(int id);
     List<DinnerTable>query(String keywoord);
     void quitTabe(int id);
}
