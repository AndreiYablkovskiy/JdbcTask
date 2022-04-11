package com.academy.model.dao;

import com.academy.model.entity.Role;

import java.util.List;

public interface RoleDao {
    void create(Role role);

    List<Role> getAll();

    Role getById(Integer id);

    void update(Role role);

    void delete(Role role);

    Role getByName(String name);
}
