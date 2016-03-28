package com.rlovep.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ViewControl implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.addObject("message", "这是为了测试视图解析器的简写原理");
		//此处只需要jsp的名字
		modelAndView.setViewName("hello");
		return modelAndView;
	}

}
