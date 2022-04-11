package com.academy.model.dao;

import com.academy.model.entity.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    List<User> getAll();

    User getById(Integer id);

    void update(User user);

    void delete(User user);

    User getByName(String name);

}
