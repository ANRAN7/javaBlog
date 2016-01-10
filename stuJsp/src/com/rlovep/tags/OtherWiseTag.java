package com.rlovep.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 
* @ClassName: OtherWiseTag
* @Description: choose的子标签,前面必须要when标签,由标志位控制
* @author peace w_peace@163.com 
* @date 22 Oct 2015 10:08:34 pm
*
 */
public class OtherWiseTag extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		boolean test=true;
		//获取父标签的test，由他的上一个when设置
		if(getParent() instanceof ChooseTag)
		{
			//获取父标签的test，由他的上一个when设置
			ChooseTag parent=(ChooseTag)getParent();
			test=parent.isFlag();
		}
		if(!test){
		  getJspBody().invoke(null);
		}
	}
   
}
