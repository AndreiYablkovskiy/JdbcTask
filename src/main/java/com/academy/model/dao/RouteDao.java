package com.academy.model.dao;

import com.academy.model.entity.Airplane;
import com.academy.model.entity.Route;

import java.util.List;

public interface RouteDao {
    void create(Route route);

    List<Route> getAll();

    Route getById(Integer id);

    void update(Route route);

    void delete(Route route);

    Integer getDeparture_id(Route route);

    Integer getArrival_id(Route route);

    List<Integer> getAirplanesId(Route route);

    List<Airplane> getRouteAirplanes(List<Integer> airplanesId);
}
