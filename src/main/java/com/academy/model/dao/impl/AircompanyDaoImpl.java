package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.AircompanyDao;
import com.academy.model.entity.Aircompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AircompanyDaoImpl implements AircompanyDao {

    @Override
    public void create(Aircompany aircompany) {
        String sql = "insert into aircompany_db.aircompany (name) value(?)";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, aircompany.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Aircompany> getAll() {
        List<Aircompany> aircompanies = new ArrayList<>();
        String sql = "select * from aircompany_db.aircompany";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Aircompany aircompany = new Aircompany();
                aircompany.setId(result.getInt(1));
                aircompany.setName(result.getString(2));
                aircompanies.add(aircompany);
            }
            return aircompanies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Aircompany getById(Integer id) {
        String sql = "SELECT * FROM aircompany_db.aircompany where id=?";
        Aircompany aircompany = new Aircompany();

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                aircompany.setId(result.getInt(1));
                aircompany.setName(result.getString(2));
                return aircompany;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Aircompany aircompany) {
        String sql = "update aircompany_db.aircompany set name=? where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, aircompany.getName());
            statement.setInt(2, aircompany.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Aircompany aircompany) {
        String sql = "delete from aircompany_db.aircompany where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, aircompany.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Aircompany getByName(String name) {
        String sql = "SELECT * FROM aircompany_db.aircompany where name=?";
        Aircompany aircompany = new Aircompany();

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                aircompany.setId(result.getInt(1));
                aircompany.setName(result.getString(2));
                return aircompany;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
