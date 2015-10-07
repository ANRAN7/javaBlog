package com.rlovep.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
//当相应的事件发生时会自动调用这些方法
/**
 * 
* @ClassName: MyHandler
* @Description: 按原样输出读入的xml文档
* @author peace w_peace@163.com 
* @date 1 Oct 2015 4:06:06 pm
*
 */
public class MyHandler2 extends DefaultHandler {
	//存储读入的xml信息
	private StringBuilder sb=new StringBuilder();
	//返回xml
	public String getxml()
	{
		return sb.toString();
	}
	
	/**
	 * 开始文档的地方
	 */
	@Override
	public void startDocument() throws SAXException {
		
		System.out.println("开始读取文档");
	}
	/**
	 * 读取xml结束标志
	 */
    @Override
    public void endDocument() throws SAXException {
    	System.out.println("文档读取完成");
    }
    /**
     * 读取标签开始时调用
     * @author peace
     * @param qName  :开始的标签名 
     * @param attributes：标签内包含的属性列表
     * 
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	//存储标签没
    	sb.append("<"+qName);
    	//判断是否有属性
    	if(attributes!=null){
    		for(int i=0;i<attributes.getLength();i++){
    			//得到属性名称
				String attrName = attributes.getQName(i);
				//得到属性值
				String attrValue = attributes.getValue(i);
				sb.append(" "+attrName+"=\""+attrValue+"\"");
    		}
    	}
    	sb.append(">");
    }
    /**
     * 读取结束标签时调用
     * @author peace
     * @param qName  :标签名 
     * 
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       sb.append("</"+qName+">");   	
    }
    /**
     * 读到文本时调用
     * @author peace
     * @param ch:表示当前读完的所有文本内容
     * @param start：当前文本内容的开始位置
     * @param length：当前文本内容的长度
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    	String text=new String(ch, start, length);
    	sb.append(text);
    }
}
