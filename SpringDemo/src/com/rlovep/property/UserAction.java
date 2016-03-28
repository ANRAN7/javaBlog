package com.rlovep.property;



public class UserAction {
	// Service: springIOC容器注入
		private UserService userService;
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		

		public String execute() {
			userService.save();
			return "peace";
		}
     
}
