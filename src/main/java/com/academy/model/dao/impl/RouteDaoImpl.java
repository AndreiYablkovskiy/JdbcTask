package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.RouteDao;
import com.academy.model.entity.Aircompany;
import com.academy.model.entity.Airplane;
import com.academy.model.entity.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    @Override
    public void create(Route route) {
        String sql = "insert into aircompany_db.route (departure_id, arrival_id) values(?,?)";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, route.getDepartureId());
            statement.setInt(2, route.getArrivalId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Route> getAll() {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT route.id, route.departure_id, route.arrival_id, " +
                "airplane_route.airplane_id, airplane.name, airplane.aircompany_id, aircompany.name " +
                "FROM aircompany_db.route " +
                "inner join airplane_route " +
                "on airplane_route.routes_id = route.id " +
                "inner join airplane " +
                "on airplane_route.airplane_id = airplane.id " +
                "inner join aircompany " +
                "on airplane.aircompany_id = aircompany.id " +
                "order by  route.id";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Airplane airplane = new Airplane();
                Aircompany aircompany = new Aircompany();
                airplane.setId(result.getInt(4));
                airplane.setName(result.getString(5));
                aircompany.setId(result.getInt(6));
                aircompany.setName(result.getString(7));
                airplane.setAircompany(aircompany);
                if (result.getRow() == 1 || !(result.getInt(1) == routes.get(routes.size() - 1).getId())) {
                    List<Airplane> routeAirplanesList = new ArrayList<>();
                    Route route = new Route();
                    route.setId(result.getInt(1));
                    route.setDepartureId(result.getInt(2));
                    route.setArrivalId(result.getInt(3));
                    routeAirplanesList.add(airplane);
                    route.setAirplanes(routeAirplanesList);
                    routes.add(route);
                } else {
                    routes.get(routes.size() - 1).getAirplanes().add(airplane);
                }
            }
            return routes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Route getById(Integer id) {
        Route route = new Route();
        String sql = "SELECT route.id, route.departure_id, route.arrival_id, " +
                "airplane_route.airplane_id, airplane.name, airplane.aircompany_id, aircompany.name " +
                "FROM aircompany_db.route " +
                "inner join airplane_route " +
                "on airplane_route.routes_id = route.id " +
                "inner join airplane " +
                "on airplane_route.airplane_id = airplane.id " +
                "inner join aircompany " +
                "on airplane.aircompany_id = aircompany.id " +
                "where route.id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Airplane airplane = new Airplane();
                Aircompany aircompany = new Aircompany();
                airplane.setId(result.getInt(4));
                airplane.setName(result.getString(5));
                aircompany.setId(result.getInt(6));
                aircompany.setName(result.getString(7));
                airplane.setAircompany(aircompany);
                if (result.getRow() == 1) {
                    List<Airplane> routeAirplanesList = new ArrayList<>();
                    route.setId(result.getInt(1));
                    route.setDepartureId(result.getInt(2));
                    route.setArrivalId(result.getInt(3));
                    routeAirplanesList.add(airplane);
                    route.setAirplanes(routeAirplanesList);
                } else {
                    route.getAirplanes().add(airplane);
                }
            }
            return route;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Route route) {
        String sql = "update aircompany_db.route set departure_id=?, arrival_id=? where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, route.getDepartureId());
            statement.setInt(2, route.getArrivalId());
            statement.setInt(3, route.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Route route) {
        String sql = "delete from aircompany_db.route where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, route.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Route> getByDepartureId(Integer departureId) {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT route.id, route.departure_id, route.arrival_id, " +
                "airplane_route.airplane_id, airplane.name, airplane.aircompany_id, aircompany.name " +
                "FROM aircompany_db.route " +
                "inner join airplane_route " +
                "on airplane_route.routes_id = route.id " +
                "inner join airplane " +
                "on airplane_route.airplane_id = airplane.id " +
                "inner join aircompany " +
                "on airplane.aircompany_id = aircompany.id " +
                "where departure_id=? " +
                "order by  route.id";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, departureId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Airplane airplane = new Airplane();
                Aircompany aircompany = new Aircompany();
                airplane.setId(result.getInt(4));
                airplane.setName(result.getString(5));
                aircompany.setId(result.getInt(6));
                aircompany.setName(result.getString(7));
                airplane.setAircompany(aircompany);
                if (result.getRow() == 1 || !(result.getInt(1) == routes.get(routes.size() - 1).getId())) {
                    List<Airplane> routeAirplanesList = new ArrayList<>();
                    Route route = new Route();
                    route.setId(result.getInt(1));
                    route.setDepartureId(result.getInt(2));
                    route.setArrivalId(result.getInt(3));
                    routeAirplanesList.add(airplane);
                    route.setAirplanes(routeAirplanesList);
                    routes.add(route);
                } else {
                    routes.get(routes.size() - 1).getAirplanes().add(airplane);
                }
            }
            return routes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Route> getByArrivalId(Integer arrivalId) {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT route.id, route.departure_id, route.arrival_id, " +
                "airplane_route.airplane_id, airplane.name, airplane.aircompany_id, aircompany.name " +
                "FROM aircompany_db.route " +
                "inner join airplane_route " +
                "on airplane_route.routes_id = route.id " +
                "inner join airplane " +
                "on airplane_route.airplane_id = airplane.id " +
                "inner join aircompany " +
                "on airplane.aircompany_id = aircompany.id " +
                "where arrival_id=? " +
                "order by  route.id";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, arrivalId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Airplane airplane = new Airplane();
                Aircompany aircompany = new Aircompany();
                airplane.setId(result.getInt(4));
                airplane.setName(result.getString(5));
                aircompany.setId(result.getInt(6));
                aircompany.setName(result.getString(7));
                airplane.setAircompany(aircompany);
                if (result.getRow() == 1 || !(result.getInt(1) == routes.get(routes.size() - 1).getId())) {
                    List<Airplane> routeAirplanesList = new ArrayList<>();
                    Route route = new Route();
                    route.setId(result.getInt(1));
                    route.setDepartureId(result.getInt(2));
                    route.setArrivalId(result.getInt(3));
                    routeAirplanesList.add(airplane);
                    route.setAirplanes(routeAirplanesList);
                    routes.add(route);
                } else {
                    routes.get(routes.size() - 1).getAirplanes().add(airplane);
                }
            }
            return routes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Route> getByAirplaneId(Airplane someAirplane) {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT route.id, route.departure_id, route.arrival_id, " +
                "airplane_route.airplane_id, airplane.name, airplane.aircompany_id, aircompany.name " +
                "FROM aircompany_db.route " +
                "inner join airplane_route " +
                "on airplane_route.routes_id = route.id " +
                "inner join airplane " +
                "on airplane_route.airplane_id = airplane.id " +
                "inner join aircompany " +
                "on airplane.aircompany_id = aircompany.id " +
                "where airplane.id=? " +
                "order by  route.id";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, someAirplane.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Airplane airplane = new Airplane();
                Aircompany aircompany = new Aircompany();
                airplane.setId(result.getInt(4));
                airplane.setName(result.getString(5));
                aircompany.setId(result.getInt(6));
                aircompany.setName(result.getString(7));
                airplane.setAircompany(aircompany);
                List<Airplane> routeAirplanesList = new ArrayList<>();
                Route route = new Route();
                route.setId(result.getInt(1));
                route.setDepartureId(result.getInt(2));
                route.setArrivalId(result.getInt(3));
                routeAirplanesList.add(airplane);
                route.setAirplanes(routeAirplanesList);
                routes.add(route);
            }
            return routes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}