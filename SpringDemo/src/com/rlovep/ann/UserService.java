package com.rlovep.ann;



import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



//@Component("userService")
@Service   // 表示业务逻辑层的组件
public class UserService {
	@Resource(name="userDao")
	private UserDao userDao; // = new UserDao();
	// IOC：对象的创建交给spring的外部容器完成

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void save() {
		userDao.save();
	}
}
