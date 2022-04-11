package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.AirplaneDao;
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
                Airplane airplane = new Airplane();
                airplane.setId(result.getInt(1));
                airplane.setName(result.getString(2));
                airplane.setAircompanyId(result.getInt(3));
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
                airplane.setId(result.getInt(1));
                airplane.setName(result.getString(2));
                airplane.setAircompanyId(result.getInt(3));
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
    public List<Airplane> getByAircompanyId(Integer aircompanyId) {
        List<Airplane> airplanes = new ArrayList<>();
        String sql = "SELECT * FROM aircompany_db.airplane where aircompany_id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, aircompanyId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Airplane airplane = new Airplane();
                airplane.setId(result.getInt(1));
                airplane.setName(result.getString(2));
                airplane.setAircompanyId(result.getInt(3));
                airplanes.add(airplane);
            }
            return airplanes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Integer> getRoutesId(Airplane airplane) {
        List<Integer> routesId = new ArrayList<>();
        String sql = "SELECT routes_id FROM aircompany_db.airplane_route where airplane_id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, airplane.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                routesId.add(result.getInt(1));
            }
            return routesId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Route> getAirplaneRoutes(List<Integer> routesId) {
        List<Route> routesList = new ArrayList<>();
        String sql = "SELECT * FROM aircompany_db.route where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (Integer id : routesId) {
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Route route = new Route();
                    route.setId(result.getInt(1));
                    route.setDepartureId(result.getInt(2));
                    route.setArrivalId(result.getInt(3));
                    routesList.add(route);
                }
            }
            return routesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
