package com.rlovep.lister;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MyRequestLister
 *
 */
@WebListener
public class MyRequestLister implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public MyRequestLister() {
    	System.out.println("Requestlister创建");
    }

	/**
     *request对象销毁
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
    	System.out.println("request对象销毁");
    	//获得放入request域对象的属性
    	ServletRequest servletRequest = sre.getServletRequest();
    	System.out.println(servletRequest.getAttribute("name"));
    }

	/**
     *request对象创建
     */
    public void requestInitialized(ServletRequestEvent sre)  { 
        System.out.println("request创建");
    }
	
}
