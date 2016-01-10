package com.rlovep.validation;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
* @ClassName: validAction
* @Description: 如果要想用struts的数据效验功能，必须继承ActionSupport或实现相关接口
* @author peace w_peace@163.com 
* @date 4 Jan 2016 10:39:55 pm
*
 */
public class validAction extends ActionSupport{
	// 封装请求数据
		private User user = new User();
		public void setUser(User user) {
			this.user = user;
		}
		public User getUser() {
			return user;
		}
		/**
		 * 对所有提交的方法都进行验证
		 */
	/*	@Override
		public void validate() {
			// 用户名非空
			if (user.getUserName() == null || "".equals(user.getUserName())) {
				// 保存错误信息
				super.addFieldError("userName", "用户名必须填写！");
			}
			// 密码
			if (user.getPwd() == null || "".equals(user.getPwd())) {
				super.addFieldError("pwd", "密码必填");
			}
		}*/
		// 对指定action校验
		/*public void validateRegister() {
			// 用户名非空
			if (user.getUserName() == null || "".equals(user.getUserName())) {
				// 保存错误信息
				super.addFieldError("userName", "用户名必须填写！");
			}
			// 密码
			if (user.getPwd() == null || "".equals(user.getPwd())) {
				super.addFieldError("pwd", "密码必填");
			}
		}*/
		// 业务方法
		public String register() {
			System.out.println(user);
			System.out.println(1);
			return SUCCESS;
		}
		
		
		// 列表展示(不用数据效验)
		public String list() {
			return SUCCESS;
		}	
}
