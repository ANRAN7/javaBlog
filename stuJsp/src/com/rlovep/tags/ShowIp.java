package com.rlovep.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 
* @ClassName: ShowIp
* @Description: 定义标签处理类，展示ip地址
* @author peace w_peace@163.com 
* @date 22 Oct 2015 7:30:21 pm
*
 */
public class ShowIp extends SimpleTagSupport {
	/**
	 * 以下屏蔽的代码在SimpleTagSupport代码中已经做了！这里不需要重复再做！
	 */
	/*private JspContext context;
	
	*//**
	 * 传入pageContext
	 *//*
	@Override
	public void setJspContext(JspContext pc) {
		this.context = pc;
	}*/
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext=(PageContext)this.getJspContext();
	    ServletRequest request = pageContext.getRequest();
	    String ip=request.getRemoteHost();
	    JspWriter out = pageContext.getOut();
	    out.write("使用自定义标签展示客户ip地址"+ip);
	    List<String> a=null;
	}
}
