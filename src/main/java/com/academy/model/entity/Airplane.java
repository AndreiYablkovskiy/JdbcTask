package com.academy.model.entity;

import java.util.List;

public class Airplane {
    private Integer id;
    private String name;
    private Integer aircompanyId;
    private List<Route> routes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAircompanyId() {
        return aircompanyId;
    }

    public void setAircompanyId(Integer aircompanyId) {
        this.aircompanyId = aircompanyId;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
