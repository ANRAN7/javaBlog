package com.rlovep.lister;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MySessionLister
 *
 */
@WebListener
public class MySessionLister implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public MySessionLister() {
        System.out.println("sessionlister创建");
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
        System.out.println("session对象创建");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
       System.out.println("session对象销毁");
       HttpSession session = se.getSession();
       System.out.println(session.getAttribute("session"));
    }
	
}
