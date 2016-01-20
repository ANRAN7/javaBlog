package com.rlovep.service.impl;

import com.rlovep.dao.impl.AdminDao;
import com.rlovep.entity.Admin;
import com.rlovep.service.IAdminService;

public class AdminService implements IAdminService {
    private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public void register(Admin admin) {
		adminDao.save(admin);
	}

	@Override
	public Admin login(Admin admin) {
		return adminDao.findByAdmin(admin);
	}
}
