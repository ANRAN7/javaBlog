package com.rlovep.forwdRedirct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/emp")		
public class EmpAction {
	@RequestMapping(value="/find")
	public String findEmpById(int id,Model model) throws Exception{
		System.out.println("查询"+id+"号员工信息");
		
		//转发到EmpAction的另一个方法中去，即再次发送请求
		return "forward:/emp/update.action";
		
		//重定向到EmpAction的另一个方法中去，即再次发送请求,id会丢失
		//return "redirect:/emp/update.action?id=" + id;

	}

	@RequestMapping(value="/update")
	public String updateEmpById(int id,Model model) throws Exception{
		System.out.println("更新" + id +"号员工信息");
		model.addAttribute("message","更新员工信息成功");
		return "ok";
	}
}
