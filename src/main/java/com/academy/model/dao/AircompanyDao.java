package com.academy.model.dao;

import com.academy.model.entity.Aircompany;

public interface AircompanyDao extends GeneralDao<Aircompany> {
    Aircompany getByName(String name);
}
