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
            statement.setInt(2, airplane.getAircompanyId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airplane> getAll() {
        List<Airplane> airplanes = new ArrayList<>();
        String sql = "select * from aircompany_db.airplane";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                List<Route> airplaneRoutesList = new ArrayList<>();
                Airplane airplane = new Airplane();
                airplane.setId(result.getInt(1));
                airplane.setName(result.getString(2));
                airplane.setAircompanyId(result.getInt(3));
                airplane.setRoutes(airplaneRoutesList);

                String routeSql = "SELECT route.id, route.departure_id, route.arrival_id " +
                        "FROM route inner join airplane_route " +
                        "on airplane_route.routes_id = route.id " +
                        "WHERE airplane_id=" + result.getInt(1);
                PreparedStatement routeStatement = connection.prepareStatement(routeSql);
                ResultSet routeResult = routeStatement.executeQuery();
                while (routeResult.next()) {
                    Route route = new Route();
                    route.setId(routeResult.getInt(1));
                    route.setDepartureId(routeResult.getInt(2));
                    route.setArrivalId(routeResult.getInt(3));
                    airplaneRoutesList.add(route);
                }
                airplanes.add(airplane);
            }
            return airplanes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Airplane getById(Integer id) {
        String sql = "SELECT * FROM aircompany_db.airplane where id=?";
        Airplane airplane = new Airplane();

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                List<Route> airplaneRoutesList = new ArrayList<>();
                airplane.setId(result.getInt(1));
                airplane.setName(result.getString(2));
                airplane.setAircompanyId(result.getInt(3));
                airplane.setRoutes(airplaneRoutesList);

                String routeSql = "SELECT route.id, route.departure_id, route.arrival_id " +
                        "FROM route inner join airplane_route " +
                        "on airplane_route.routes_id = route.id " +
                        "WHERE airplane_id=" + result.getInt(1);
                PreparedStatement routeStatement = connection.prepareStatement(routeSql);
                ResultSet routeResult = routeStatement.executeQuery();
                while (routeResult.next()) {
                    Route route = new Route();
                    route.setId(routeResult.getInt(1));
                    route.setDepartureId(routeResult.getInt(2));
                    route.setArrivalId(routeResult.getInt(3));
                    airplaneRoutesList.add(route);
                }
                return airplane;
            }
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
    public List<Airplane> getByAircompanyId(Aircompany aircompany) {
        List<Airplane> airplanes = new ArrayList<>();
        String sql = "SELECT * FROM aircompany_db.airplane where aircompany_id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, aircompany.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                List<Route> airplaneRoutesList = new ArrayList<>();
                Airplane airplane = new Airplane();
                airplane.setId(result.getInt(1));
                airplane.setName(result.getString(2));
                airplane.setAircompanyId(result.getInt(3));
                airplane.setRoutes(airplaneRoutesList);

                String routeSql = "SELECT route.id, route.departure_id, route.arrival_id " +
                        "FROM route inner join airplane_route " +
                        "on airplane_route.routes_id = route.id " +
                        "WHERE airplane_id=" + result.getInt(1);
                PreparedStatement routeStatement = connection.prepareStatement(routeSql);
                ResultSet routeResult = routeStatement.executeQuery();
                while (routeResult.next()) {
                    Route route = new Route();
                    route.setId(routeResult.getInt(1));
                    route.setDepartureId(routeResult.getInt(2));
                    route.setArrivalId(routeResult.getInt(3));
                    airplaneRoutesList.add(route);
                }
                airplanes.add(airplane);
            }
            return airplanes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Airplane> getByRouteId(Route route) {
        List<Airplane> airplanes = new ArrayList<>();
        String sql = "SELECT airplane.id, airplane.name, airplane.aircompany_id " +
                "FROM airplane_route " +
                "inner join airplane " +
                "on airplane_route.airplane_id = airplane.id " +
                "where routes_id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, route.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                List<Route> airplaneRoutesList = new ArrayList<>();
                Airplane airplane = new Airplane();
                airplane.setId(result.getInt(1));
                airplane.setName(result.getString(2));
                airplane.setAircompanyId(result.getInt(3));
                airplane.setRoutes(airplaneRoutesList);

                String routeSql = "SELECT route.id, route.departure_id, route.arrival_id " +
                        "FROM route inner join airplane_route " +
                        "on airplane_route.routes_id = route.id " +
                        "WHERE airplane_id=" + result.getInt(1);
                PreparedStatement routeStatement = connection.prepareStatement(routeSql);
                ResultSet routeResult = routeStatement.executeQuery();
                while (routeResult.next()) {
                    Route newRoute = new Route();
                    newRoute.setId(routeResult.getInt(1));
                    newRoute.setDepartureId(routeResult.getInt(2));
                    newRoute.setArrivalId(routeResult.getInt(3));
                    airplaneRoutesList.add(newRoute);
                }
                airplanes.add(airplane);
            }
            return airplanes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

