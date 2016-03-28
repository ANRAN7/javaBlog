package com.rlovep.ann;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component("userDao")
@Repository   // 在持久层可以选择用这个注解
public class UserDao {
	public void save() {
		System.out.println("DB:保存用户");
	}
}

