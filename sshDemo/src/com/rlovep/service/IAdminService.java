package com.rlovep.service;

import com.rlovep.entity.Admin;

public interface IAdminService {
	/**
	 * 注册
	 * @param admin
	 */
	void register(Admin admin);

	/**
	 * 登陆
	 * @param admin
	 * @return
	 */
	Admin login(Admin admin);
}
