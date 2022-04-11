package com.academy.model.entity;

import java.util.List;

public class Route {
    private Integer id;
    private Integer departureId;
    private Integer arrivalId;
    private List<Airplane> airplanes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartureId() {
        return departureId;
    }

    public void setDepartureId(Integer departureId) {
        this.departureId = departureId;
    }

    public Integer getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(Integer arrivalId) {
        this.arrivalId = arrivalId;
    }

    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(List<Airplane> airplanes) {
        this.airplanes = airplanes;
    }
}
