package com.rlovep.contacrt.service.impl;
import java.util.List;

import com.rlovep.contacrt.service.ContactService;
import com.rlovep.contact.dao.ContactDao;
import com.rlovep.contact.dao.impl.ContactDaoImpl;
import com.rlovep.contact.entity.Contact;
import com.rlovep.contact.exception.NameRepeatException;
/**
 * 
* @ClassName: ContactServiceImpl
* @Description: 业务逻辑处理层
* @author peace w_peace@163.com 
* @date 23 Oct 2015 11:09:05 am
*
 */
public class ContactServiceImpl implements ContactService {
	private ContactDao dao=new ContactDaoImpl();
	@Override
	public void addContact(Contact contact) throws NameRepeatException {
         if(dao.checkContact(contact.getName()))
         {
        	//重复
 			/**
 			 * 注意： 如果业务层方法出现任何业务异常，则返回标记（自定义异常）到servlet
 			 */
        	 throw new NameRepeatException("名字重复异常");
         }
         dao.addContact(contact);
	}

	@Override
	public void updateContact(Contact contact) {
		dao.updateContact(contact);
	}

	@Override
	public void deleteContact(String id) {
		dao.deleteContact(id);
	}

	@Override
	public List<Contact> findAll() {
		return dao.findAll();
	}

	@Override
	public Contact findById(String id) {
		return dao.findById(id);
	}

	
}
