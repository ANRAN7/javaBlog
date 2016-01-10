package com.rlovep.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
* @ClassName: ApiAction1
* @Description: 使用接口访问Servlet的API
* @author peace w_peace@163.com 
* @date 26 Dec 2015 3:19:38 pm
*
 */
public class ApiAction1 extends ActionSupport implements RequestAware,SessionAware,ApplicationAware{

	//定义关联的Map结果
	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;
	//获得关联的map结果
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application=application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
        this.session=session;
	}

	@Override
	public void setRequest(Map<String, Object> resquest) {
		this.request=resquest;
	}
	@Override
	public String execute() throws Exception {
		
		
	 	// 数据
	 	request.put("request_data", "request_data1_actionAware");
		session.put("session_data", "session_data1_actionAware");
		application.put("application_data", "application_data1_actionAware");
//		
		return SUCCESS;
	}

}
