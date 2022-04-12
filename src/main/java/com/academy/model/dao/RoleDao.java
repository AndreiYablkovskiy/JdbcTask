package com.academy.model.dao;

import com.academy.model.entity.Role;

public interface RoleDao extends GeneralDao<Role> {
    Role getByName(String name);
}
