package com.academy.model.dao;

import com.academy.model.entity.Aircompany;
import com.academy.model.entity.Airplane;
import com.academy.model.entity.Route;

import java.util.List;

public interface AirplaneDao extends GeneralDao<Airplane> {
    List<Airplane> getByAircompanyId(Aircompany aircompany);

    List<Airplane> getByRouteId(Route route);
}
