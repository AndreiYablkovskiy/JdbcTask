package com.academy.model.dao;

import com.academy.model.entity.Aircompany;

import java.util.List;

public interface AircompanyDao {
    void create(Aircompany aircompany);

    List<Aircompany> getAll();

    Aircompany getById(Integer id);

    void update(Aircompany aircompany);

    void delete(Aircompany aircompany);

    Aircompany getByName(String name);
}
