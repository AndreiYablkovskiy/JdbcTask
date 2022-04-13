package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.AirplaneDao;
import com.academy.model.entity.Aircompany;
import com.academy.model.entity.Airplane;
import com.academy.model.entity.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneDaoImpl implements AirplaneDao {
    @Override
    public void create(Airplane airplane) {
        String sql = "insert into aircompany_db.airplane (name, aircompany_id) values(?,?)";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, airplane.getName());
            statement.setInt(2, airplane.getAircompany().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airplane> getAll() {
        List<Airplane> airplanes = new ArrayList<>();
        String sql = "SELECT   airplane.id, airplane.name, airplane.aircompany_id, aircompany.name, " +
                "airplane_route.routes_id, route.departure_id, route.arrival_id " +
                "FROM aircompany_db.airplane " +
                " inner join aircompany  " +
                "on airplane.aircompany_id = aircompany.id " +
                "inner join airplane_route " +
                "on airplane_route.airplane_id = airplane.id " +
                "inner join route " +
                "on airplane_route.routes_id = route.id " +
                "order by  airplane.id";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Route route = new Route();
                route.setId(result.getInt(5));
                route.setDepartureId(result.getInt(6));
                route.setArrivalId(result.getInt(7));
                if (result.getRow() == 1 || !(result.getInt(1) == airplanes.get(airplanes.size() - 1).getId())) {
                    List<Route> airplaneRoutesList = new ArrayList<>();
                    Airplane airplane = new Airplane();
                    Aircompany aircompany = new Aircompany();
                    airplane.setId(result.getInt(1));
                    airplane.setName(result.getString(2));
                    aircompany.setId(result.getInt(3));
                    aircompany.setName(result.getString(4));
                    airplane.setAircompany(aircompany);
                    airplaneRoutesList.add(route);
                    airplane.setRoutes(airplaneRoutesList);
                    airplanes.add(airplane);
                } else {
                    airplanes.get(airplanes.size() - 1).getRoutes().add(route);
                }
            }
            return airplanes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Airplane getById(Integer id) {
        Airplane airplane = new Airplane();
        String sql = "SELECT   airplane.id, airplane.name, airplane.aircompany_id, aircompany.name, " +
                "airplane_route.routes_id, route.departure_id, route.arrival_id " +
                "FROM aircompany_db.airplane " +
                " inner join aircompany  " +
                "on airplane.aircompany_id = aircompany.id " +
                "inner join airplane_route " +
                "on airplane_route.airplane_id = airplane.id " +
                "inner join route " +
                "on airplane_route.routes_id = route.id " +
                "where airplane.id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Route route = new Route();
                route.setId(result.getInt(5));
                route.setDepartureId(result.getInt(6));
                route.setArrivalId(result.getInt(7));
                if (result.getRow() == 1) {
                    List<Route> airplaneRoutesList = new ArrayList<>();
                    Aircompany aircompany = new Aircompany();
                    airplane.setId(result.getInt(1));
                    airplane.setName(result.getString(2));
                    aircompany.setId(result.getInt(3));
                    aircompany.setName(result.getString(4));
                    airplane.setAircompany(aircompany);
                    airplaneRoutesList.add(route);
                    airplane.setRoutes(airplaneRoutesList);
                } else {
                    airplane.getRoutes().add(route);
                }
            }
            return airplane;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Airplane airplane) {
        String sql = "update aircompany_db.airplane set name=? where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, airplane.getName());
            statement.setInt(2, airplane.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Airplane airplane) {
        String sql = "delete from aircompany_db.airplane where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, airplane.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airplane> getByAircompanyId(Aircompany someAircompany) {
        List<Airplane> airplanes = new ArrayList<>();
        String sql = "SELECT   airplane.id, airplane.name, airplane.aircompany_id, aircompany.name, " +
                "airplane_route.routes_id, route.departure_id, route.arrival_id " +
                "FROM aircompany_db.airplane " +
                " inner join aircompany  " +
                "on airplane.aircompany_id = aircompany.id " +
                "inner join airplane_route " +
                "on airplane_route.airplane_id = airplane.id " +
                "inner join route " +
                "on airplane_route.routes_id = route.id " +
                "where aircompany.id=? " +
                "order by  airplane.id";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, someAircompany.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Route route = new Route();
                route.setId(result.getInt(5));
                route.setDepartureId(result.getInt(6));
                route.setArrivalId(result.getInt(7));
                if (result.getRow() == 1 || !(result.getInt(1) == airplanes.get(airplanes.size() - 1).getId())) {
                    List<Route> airplaneRoutesList = new ArrayList<>();
                    Airplane airplane = new Airplane();
                    Aircompany aircompany = new Aircompany();
                    airplane.setId(result.getInt(1));
                    airplane.setName(result.getString(2));
                    aircompany.setId(result.getInt(3));
                    aircompany.setName(result.getString(4));
                    airplane.setAircompany(aircompany);
                    airplaneRoutesList.add(route);
                    airplane.setRoutes(airplaneRoutesList);
                    airplanes.add(airplane);
                } else {
                    airplanes.get(airplanes.size() - 1).getRoutes().add(route);
                }
            }
            return airplanes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Airplane> getByRouteId(Route someRoute) {
        List<Airplane> airplanes = new ArrayList<>();
        String sql = "SELECT   airplane.id, airplane.name, airplane.aircompany_id, aircompany.name, " +
                "airplane_route.routes_id, route.departure_id, route.arrival_id " +
                "FROM aircompany_db.airplane " +
                " inner join aircompany  " +
                "on airplane.aircompany_id = aircompany.id " +
                "inner join airplane_route " +
                "on airplane_route.airplane_id = airplane.id " +
                "inner join route " +
                "on airplane_route.routes_id = route.id " +
                "where route.id=? " +
                "order by  airplane.id";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, someRoute.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Route route = new Route();
                route.setId(result.getInt(5));
                route.setDepartureId(result.getInt(6));
                route.setArrivalId(result.getInt(7));
                List<Route> airplaneRoutesList = new ArrayList<>();
                Airplane airplane = new Airplane();
                Aircompany aircompany = new Aircompany();
                airplane.setId(result.getInt(1));
                airplane.setName(result.getString(2));
                aircompany.setId(result.getInt(3));
                aircompany.setName(result.getString(4));
                airplane.setAircompany(aircompany);
                airplaneRoutesList.add(route);
                airplane.setRoutes(airplaneRoutesList);
                airplanes.add(airplane);
            }
            return airplanes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

