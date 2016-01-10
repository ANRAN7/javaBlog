package com.rlovep.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 
* @ClassName: ChooseTag
* @Description: 演示choose标签，包含有子标签when
* @author peace w_peace@163.com 
* @date 22 Oct 2015 9:56:07 pm
*
 */
public class ChooseTag extends SimpleTagSupport{
	//此去时变量不是标签属性，由when标签更改；othewise获得；
	private boolean flag;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// Choose标签作用显示标签体，以及作为其他两个标签的父标签；
		getJspBody().invoke(null);
	}
}
