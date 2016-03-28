package com.rlovep.hello;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloControl implements Controller{

	public HelloControl() {
		System.out.println("创建helloControl");
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("进入处理方法");
		/**
		 * ModelAndView表示向视图封装的数据和真实路径
		 */
		ModelAndView modelAndView=new ModelAndView();
		//写入一个域对象
		modelAndView.addObject("message","HelloWorld");
		//真实路径
		modelAndView.setViewName("/jsp/hello.jsp");
		return modelAndView;
	}

}
