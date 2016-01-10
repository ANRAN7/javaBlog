package com.rlovep.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class AttributeTags extends SimpleTagSupport{
	//声明属性的成员变量
	private Integer value;
	private String name;
	//关键点： 必须提供公开的setter方法，用于给属性赋值
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    @Override
    public void doTag() throws JspException, IOException {
    	JspContext jspContext2 = this.getJspContext();
    	//将属性输出：
    	jspContext2.getOut().println();
    	jspContext2.getOut().write("\n"+name+"="+value);
    	//得到标签体内容
    	JspFragment jspBody = getJspBody();
    	//显示标签体的两种方法
    	//方法1直接调用
    	//jspBody.invoke(null);
    	//方法2通过输出到out
    	//jspBody.invoke(jspContext2.getOut());
    	//修改标签体内容并且重复输出：
    	StringWriter sw=new StringWriter();
    	jspBody.invoke(sw);
    	String s=sw.toString().toUpperCase();
    	for(int i=0;i<5;i++)
    	{
    		//注意此去不能在调用jspBody.invoke();
    		jspContext2.getOut().write(s);
    	}
    }
}
