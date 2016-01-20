package com.rlovep.dao;

import com.rlovep.entity.Admin;

public interface IAdminDao {
    public void save(Admin admin);
    public Admin findByAdmin(Admin admin);
}
