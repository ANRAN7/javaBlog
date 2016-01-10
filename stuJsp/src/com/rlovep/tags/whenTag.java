package com.rlovep.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 
* @ClassName: whenTag
* @Description: when标签必须带有父标签
* @author peace w_peace@163.com 
* @date 22 Oct 2015 10:03:48 pm
*
 */
public class whenTag extends SimpleTagSupport{
	//增加test属性
	private boolean test;

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}
	@Override
	public void doTag() throws JspException, IOException {
		//如果标签属性为true，显示标签体
	  if(test){
		  getJspBody().invoke(null);
	  }
	  //设置父标签给otherwise用
	  ChooseTag parent=null;
	  if(getParent() instanceof ChooseTag){
		  parent=(ChooseTag)getParent();
		 parent.setFlag(test);
	  }
	  
	}
	
}
