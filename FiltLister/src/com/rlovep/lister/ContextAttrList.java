package com.rlovep.lister;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ContextAttrList
 *
 */
@WebListener
public class ContextAttrList implements ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public ContextAttrList() {
    	System.out.println("ContextAttrList创建");
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent scae)  { 
    	System.out.println("context域对象新增");
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent scae)  { 
    	System.out.println("context域对象移除");
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent scae)  { 
    	System.out.println("context域对象替换");
    }
	
}
