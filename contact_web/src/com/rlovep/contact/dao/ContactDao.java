package com.rlovep.contact.dao;

import java.util.List;

import com.rlovep.contact.entity.Contact;
/**
 * 
* @ClassName: ContactDao
* @Description: 连接数据库的接口
* @author peace w_peace@163.com 
* @date 20 Oct 2015 4:02:01 pm
*
 */
public interface ContactDao {
	public void addContact(Contact contact);//添加联系人
	public void updateContact(Contact contact);//修改联系人
	public void deleteContact(String id);//删除联系人
	public List<Contact> findAll();  //查询所有联系人
	public Contact findById(String id);//根据编号查询联系人
	
}
