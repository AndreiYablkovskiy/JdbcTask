package com.academy.model.dao;

import com.academy.model.entity.Airplane;
import com.academy.model.entity.Route;

import java.util.List;

public interface RouteDao extends GeneralDao<Route> {
    List<Route> getByDepartureId(Integer departureId);

    List<Route> getByArrivalId(Integer arrivalId);

    List<Route> getByAirplaneId(Airplane airplane);
}
