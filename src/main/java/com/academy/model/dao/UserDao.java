package com.academy.model.dao;

import com.academy.model.entity.User;

public interface UserDao extends GeneralDao<User> {
    User getByName(String name);

}
