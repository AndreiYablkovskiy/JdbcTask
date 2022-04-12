package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.RouteDao;
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
        String sql = "select * from aircompany_db.route";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                List<Airplane> routeAirplanesList = new ArrayList<>();
                Route route = new Route();
                route.setId(result.getInt(1));
                route.setDepartureId(result.getInt(2));
                route.setArrivalId(result.getInt(3));
                route.setAirplanes(routeAirplanesList);

                String routeSql = "SELECT airplane.id, airplane.name, airplane.aircompany_id " +
                        "FROM airplane inner join airplane_route " +
                        "on airplane_route.airplane_id = airplane.id " +
                        "WHERE routes_id=" + result.getInt(1);
                PreparedStatement routeStatement = connection.prepareStatement(routeSql);
                ResultSet airplaneResult = routeStatement.executeQuery();
                while (airplaneResult.next()) {
                    Airplane airplane = new Airplane();
                    airplane.setId(airplaneResult.getInt(1));
                    airplane.setName(airplaneResult.getString(2));
                    airplane.setAircompanyId(airplaneResult.getInt(3));
                    routeAirplanesList.add(airplane);
                }
                routes.add(route);
            }
            return routes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Route getById(Integer id) {
        String sql = "SELECT * FROM aircompany_db.route where id=?";
        Route route = new Route();

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                List<Airplane> routeAirplanesList = new ArrayList<>();
                route.setId(result.getInt(1));
                route.setDepartureId(result.getInt(2));
                route.setArrivalId(result.getInt(3));
                route.setAirplanes(routeAirplanesList);

                String routeSql = "SELECT airplane.id, airplane.name, airplane.aircompany_id " +
                        "FROM airplane inner join airplane_route " +
                        "on airplane_route.airplane_id = airplane.id " +
                        "WHERE routes_id=" + result.getInt(1);
                PreparedStatement routeStatement = connection.prepareStatement(routeSql);
                ResultSet airplaneResult = routeStatement.executeQuery();
                while (airplaneResult.next()) {
                    Airplane airplane = new Airplane();
                    airplane.setId(airplaneResult.getInt(1));
                    airplane.setName(airplaneResult.getString(2));
                    airplane.setAircompanyId(airplaneResult.getInt(3));
                    routeAirplanesList.add(airplane);
                }
                return route;
            }
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
        String sql = "SELECT * FROM aircompany_db.route where departure_id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, departureId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                List<Airplane> routeAirplanesList = new ArrayList<>();
                Route route = new Route();
                route.setId(result.getInt(1));
                route.setDepartureId(result.getInt(2));
                route.setArrivalId(result.getInt(3));
                route.setAirplanes(routeAirplanesList);

                String routeSql = "SELECT airplane.id, airplane.name, airplane.aircompany_id " +
                        "FROM airplane inner join airplane_route " +
                        "on airplane_route.airplane_id = airplane.id " +
                        "WHERE routes_id=" + result.getInt(1);
                PreparedStatement routeStatement = connection.prepareStatement(routeSql);
                ResultSet airplaneResult = routeStatement.executeQuery();
                while (airplaneResult.next()) {
                    Airplane airplane = new Airplane();
                    airplane.setId(airplaneResult.getInt(1));
                    airplane.setName(airplaneResult.getString(2));
                    airplane.setAircompanyId(airplaneResult.getInt(3));
                    routeAirplanesList.add(airplane);
                }
                routes.add(route);
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
        String sql = "SELECT * FROM aircompany_db.route where arrival_id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, arrivalId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                List<Airplane> routeAirplanesList = new ArrayList<>();
                Route route = new Route();
                route.setId(result.getInt(1));
                route.setDepartureId(result.getInt(2));
                route.setArrivalId(result.getInt(3));
                route.setAirplanes(routeAirplanesList);

                String routeSql = "SELECT airplane.id, airplane.name, airplane.aircompany_id " +
                        "FROM airplane inner join airplane_route " +
                        "on airplane_route.airplane_id = airplane.id " +
                        "WHERE routes_id=" + result.getInt(1);
                PreparedStatement routeStatement = connection.prepareStatement(routeSql);
                ResultSet airplaneResult = routeStatement.executeQuery();
                while (airplaneResult.next()) {
                    Airplane airplane = new Airplane();
                    airplane.setId(airplaneResult.getInt(1));
                    airplane.setName(airplaneResult.getString(2));
                    airplane.setAircompanyId(airplaneResult.getInt(3));
                    routeAirplanesList.add(airplane);
                }
                routes.add(route);
            }
            return routes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Route> getByAirplaneId(Airplane airplane) {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT route.id, route.departure_id, route.arrival_id " +
                "FROM airplane_route " +
                "inner join route " +
                "on airplane_route.routes_id = route.id " +
                "where airplane_id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, airplane.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                List<Airplane> routeAirplanesList = new ArrayList<>();
                Route route = new Route();
                route.setId(result.getInt(1));
                route.setDepartureId(result.getInt(2));
                route.setArrivalId(result.getInt(3));
                route.setAirplanes(routeAirplanesList);

                String routeSql = "SELECT airplane.id, airplane.name, airplane.aircompany_id " +
                        "FROM airplane inner join airplane_route " +
                        "on airplane_route.airplane_id = airplane.id " +
                        "WHERE routes_id=" + result.getInt(1);
                PreparedStatement routeStatement = connection.prepareStatement(routeSql);
                ResultSet airplaneResult = routeStatement.executeQuery();
                while (airplaneResult.next()) {
                    Airplane newAirplane = new Airplane();
                    newAirplane.setId(airplaneResult.getInt(1));
                    newAirplane.setName(airplaneResult.getString(2));
                    newAirplane.setAircompanyId(airplaneResult.getInt(3));
                    routeAirplanesList.add(newAirplane);
                }
                routes.add(route);
            }
            return routes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}