package com.rlovep.ann;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

//@Component("userAction")//加入Ioc容器,相当于bean
@Controller//控制器的组件
public class UserAction {
	// Service: springIOC容器注入
	    @Resource(name="userService")//相当于property中的ref参考属性
		private UserService userService;
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		

		public String execute() {
			userService.save();
			return "peace";
		}
     
}
