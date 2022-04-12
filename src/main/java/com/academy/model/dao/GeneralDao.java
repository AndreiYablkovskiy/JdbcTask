package com.academy.model.dao;

import java.util.List;

public interface GeneralDao<T> {
    void create(T entity);

    List<T> getAll();

    T getById(Integer id);

    void update(T entity);

    void delete(T entity);
}
