package com.rlovep.mapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MappCotrol implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("操作用户");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message","操作用户成功");
		modelAndView.setViewName("/jsp/hello.jsp");
		return modelAndView;
	}

}
