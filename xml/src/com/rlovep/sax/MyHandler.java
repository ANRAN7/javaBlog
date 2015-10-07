package com.rlovep.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
//当相应的事件发生时会自动调用这些方法
/**
 * 
* @ClassName: MyHandler
* @Description: Sax处理程序，辞去只是简单是处理
* @author peace w_peace@163.com 
* @date 1 Oct 2015 4:06:06 pm
*
 */
public class MyHandler extends DefaultHandler {
	
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
    	System.out.println("输出开始标签："+qName);
    }
    /**
     * 读取结束标签时调用
     * @author peace
     * @param qName  :标签名 
     * 
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       System.out.println("输出结束标签："+qName);    	
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
    	System.out.println("文本内容："+text);
    }
}
