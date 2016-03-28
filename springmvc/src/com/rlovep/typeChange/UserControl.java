package com.rlovep.typeChange;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/type")
public class UserControl {
	public UserControl() {
		System.out.println("构造control");
	}
	@InitBinder
	protected void changeDate(HttpServletRequest request,ServletRequestDataBinder binder){
		binder.registerCustomEditor(
				Date.class, 
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),true));
	}
	@RequestMapping(value="/add")
    public String add(Model model,User user){
    	model.addAttribute("message", user.toString());
    	return "hello";
    }
}
