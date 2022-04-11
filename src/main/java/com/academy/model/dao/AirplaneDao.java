package com.academy.model.dao;

import com.academy.model.entity.Airplane;
import com.academy.model.entity.Route;

import java.util.List;

public interface AirplaneDao {
    void create(Airplane airplane);

    List<Airplane> getAll();

    Airplane getById(Integer id);

    void update(Airplane airplane);

    void delete(Airplane airplane);

    List<Airplane> getByAircompanyId(Integer aircompanyId);

    List<Integer> getRoutesId(Airplane airplane);

    List<Route> getAirplaneRoutes(List<Integer> routesId);
}
