package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.UserDao;
import com.academy.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void create(User user) {
        String sql = "insert into aircompany_db.user (name, role_id) values(?,?)";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setInt(2, user.getRoleId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from aircompany_db.user";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                user.setRoleId(result.getInt(3));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getById(Integer id) {
        String sql = "SELECT * FROM aircompany_db.user where id=?";
        User user = new User();

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                user.setRoleId(result.getInt(3));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(User user) {
        String sql = "update aircompany_db.user set name=? where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        String sql = "delete from aircompany_db.user where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getByName(String name) {
        String sql = "SELECT * FROM aircompany_db.user where name=?";
        User user = new User();

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                user.setRoleId(result.getInt(3));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
