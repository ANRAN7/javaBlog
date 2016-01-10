/*
 * $Id: DefaultActionSupport.java 651946 2008-04-27 13:41:38Z apetrelli $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts2.dispatcher.ng.filter;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.dispatcher.ng.ExecuteOperations;
import org.apache.struts2.dispatcher.ng.InitOperations;
import org.apache.struts2.dispatcher.ng.PrepareOperations;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Handles both the preparation and execution phases of the Struts dispatching process.  This filter is better to use
 * when you don't have another filter that needs access to action context information, such as Sitemesh.
 */
public class StrutsPrepareAndExecuteFilter implements StrutsStatics, Filter {
    protected PrepareOperations prepare;
    protected ExecuteOperations execute;
    protected List<Pattern> excludedPatterns = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        InitOperations init = new InitOperations();
        Dispatcher dispatcher = null;
        try {
        	//包装javax.servlet.FilterConfig对象
            FilterHostConfig config = new FilterHostConfig(filterConfig);
            //初始化日志
            init.initLogging(config);
            //创建Dispatcher 包含了2方面的信息：servletcontext，拦截器的配置参数
            //同时指定了初始化配置文件的顺序
            dispatcher = init.initDispatcher(config);
            init.initStaticContentLoader(config, dispatcher);
            //PrepareOperations为请求处理做一些准备工作
            prepare = new PrepareOperations(filterConfig.getServletContext(), dispatcher);
            //执行器
            execute = new ExecuteOperations(filterConfig.getServletContext(), dispatcher);
          //哪些请求是被过滤掉的， 不执行拦截器
            this.excludedPatterns = init.buildExcludedPatternsList(dispatcher);
            //回调方法
            postInit(dispatcher, filterConfig);
        } finally {
            if (dispatcher != null) {
                dispatcher.cleanUpAfterInit();
            }
            init.cleanup();
        }
    }

    /**
     * Callback for post initialization
     */
    protected void postInit(Dispatcher dispatcher, FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
         
        try {
        	
            if (excludedPatterns != null && prepare.isUrlExcluded(request, excludedPatterns)) {
                chain.doFilter(request, response);
            } else {
            	 //设置编码和国际化 
                prepare.setEncodingAndLocale(request, response);
               //创建Action上下文（重点）  
                prepare.createActionContext(request, response);
              //Dispater有一个静态的Threadlocal变量，为每个线程保存一个副本
                prepare.assignDispatcherToThread();
                //包装request
                request = prepare.wrapRequest(request);
             // 得到action 的 mapping  
                ActionMapping mapping = prepare.findActionMapping(request, response, true);
                if (mapping == null) {
                    boolean handled = execute.executeStaticResourceRequest(request, response);
                    if (!handled) {
                    	//如果存在对应的action则放行
                        chain.doFilter(request, response);
                    }
                } else {
                    //处理请求，读取配置文件，利用反射机制生成一个action代理
                    execute.executeAction(request, response, mapping);
                }
            }
        } finally {
            prepare.cleanupRequest(request);
        }
    }

    public void destroy() {
        prepare.cleanupDispatcher();
    }

}
