package com.rlovep.contact.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.rlovep.contact.dao.ContactDao;
import com.rlovep.contact.entity.Contact;
import com.rlovep.contact.util.XMLUtil;

/**
 * 
* @ClassName: ContactDaoImpl
* @Description: 数据库接口的具体实现：此去用xml代替数据库
* @author peace w_peace@163.com 
* @date 20 Oct 2015 4:02:41 pm
*
 */
public class ContactDaoImpl implements ContactDao  {

	/**
	 * 增加联系人
	 */
	@Override
	public void addContact(Contact contact) {
		//创建操作引用
		Document doc=null;
		Element rootelem=null;
		//判断对应的xml文件是否存在
		File file=new File("/home/peace/workspace/contact.xml");
		if(!file.exists())
		{
			/*
			 * 文件不存在则创建
			 */
			doc=DocumentHelper.createDocument();
			//创建根标签
			rootelem=doc.addElement("contactlist");
		}
		else{
			//存在就获得xml文档
			doc=XMLUtil.getDocument();
			//获得根标签
			rootelem=doc.getRootElement();
		}
		//添加contact标签
		/*
		 * <contact id="1">
				<name>eric</name>
				<gender>男</gender>
				<age>20</age>
				<phone>1343333</phone>
				<email>eric@qq.com</email>
				<qq>554444</qq>
			</contact>
		 */
		//增加一个联系人的标签
		Element contactElem=rootelem.addElement("contact");
		/*
		 * 由系统生成默认的id给联系人
		 */
		String uuid=UUID.randomUUID().toString().replace("-", ",");
		//添加属性id
		contactElem.addAttribute("id", uuid);
		//下面都是添加对应的标签
		contactElem.addElement("name").setText(contact.getName());
		contactElem.addElement("gender").setText(contact.getGender());
		contactElem.addElement("age").setText(contact.getAge()+"");
		contactElem.addElement("phone").setText(contact.getPhone());
		contactElem.addElement("email").setText(contact.getEmail());
		contactElem.addElement("qq").setText(contact.getQq());
		//将修改后的xml写回文件
		XMLUtil.writexml(doc);
	}
    /**
     * 更新contact标签
     */
	@Override
	public void updateContact(Contact contact) {
		// TODO Auto-generated method stub
		/*
		 * 需求： 修改id值为2的联系人
		 * 	1）查询id值为指定id的contact标签
		 *  2）修改contact标签的内容
		 */
		Document doc=XMLUtil.getDocument();
		//更新contact标签
		Element contactElem=(Element) doc.selectSingleNode("//contact[@id='"+contact.getId()+"']");
		contactElem.element("name").setText(contact.getName());
		contactElem.element("gender").setText(contact.getGender());
		contactElem.element("age").setText(contact.getAge()+"");
		contactElem.element("phone").setText(contact.getPhone());
		contactElem.element("email").setText(contact.getEmail());
		contactElem.element("qq").setText(contact.getQq());
		XMLUtil.writexml(doc);
	}
	/**
	 * 删除contact标签
	 */
	@Override
	public void deleteContact(String id) {
		Document doc=XMLUtil.getDocument();
		Element contactElem=(Element) doc.selectSingleNode("//contact[@id='"+id+"']");
		if(contactElem!=null)
		{
			contactElem.detach();
		}
		XMLUtil.writexml(doc);
	}
     /**
      * 列出所有contact标签
      */
	@Override
	public List<Contact> findAll() {
		Document doc=XMLUtil.getDocument();
		List<Contact> contacts=new ArrayList<>();
		//通过xpath找到所有的contact标签
		List<Element> eList = (List<Element>)doc.selectNodes("//contact");
		//循环列出所有
		for(Element e:eList){
			Contact c=new Contact();
			c.setId(e.attributeValue("id"));
			c.setName(e.elementText("name"));
			c.setGender(e.elementText("gender"));
			c.setAge(Integer.parseInt(e.elementText("age")));
			c.setPhone(e.elementText("phone"));
			c.setEmail(e.elementText("email"));
			c.setQq(e.elementText("qq"));
			//把Contact放入list中
			contacts.add(c);
		}
		return contacts;
	}
    /**
     * 寻找到特定id的标签
     * 
     */
	@Override
	public Contact findById(String id) {
		Document doc = XMLUtil.getDocument();
		Element e = (Element)doc.selectSingleNode("//contact[@id='"+id+"']");
		
		Contact c = null;
		if(e!=null){
			//创建COntact对象
			c = new Contact();
			c.setId(e.attributeValue("id"));
			c.setName(e.elementText("name"));
			c.setGender(e.elementText("gender"));
			c.setAge(Integer.parseInt(e.elementText("age")));
			c.setPhone(e.elementText("phone"));
			c.setEmail(e.elementText("email"));
			c.setQq(e.elementText("qq"));
		}
		return c;
	}

}
