package com.rlovep.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//控制器类注明，相当于xml中的bean
@Controller
@RequestMapping(value="hello")
public class HelloAnn {

	/**
	 * 业务方法
	 * 只要是/hello.action的请求，都交由HelloAction对象中的hello()方法去处理
	 */
	@RequestMapping(value="/hello.action")
	public String hello(Model model)throws Exception{
		System.out.println("HelloAction:helloMethod");
		model.addAttribute("message", "这是使用注解的helloworld");
		return "hello";
	}
	/**
	 * 业务方法
	 * 只要是/bye.action的请求，都交由HelloAction对象中的bye()方法去处理
	 */
	@RequestMapping(value="/bye.action")   
	public String bye(Model model) throws Exception{
		System.out.println("HelloAction::bye()");
		model.addAttribute("message","再见");
		return "hello";
	}
}
