package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.UserDao;
import com.academy.model.entity.Role;
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
            statement.setInt(2, user.getRole().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user.id, user.name, user.role_id, role.name " +
                "from user " +
                "inner join role " +
                "on user.role_id = role.id";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User();
                Role role = new Role();
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                role.setId(result.getInt(3));
                role.setName(result.getString(4));
                user.setRole(role);
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
        User user = new User();
        String sql = "SELECT user.id, user.name, user.role_id, role.name " +
                "from user " +
                "inner join role " +
                "on user.role_id = role.id " +
                "where user.id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Role role = new Role();
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                role.setId(result.getInt(3));
                role.setName(result.getString(4));
                user.setRole(role);
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
        User user = new User();
        String sql = "SELECT user.id, user.name, user.role_id, role.name " +
                "from user " +
                "inner join role " +
                "on user.role_id = role.id " +
                "where user.name=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Role role = new Role();
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                role.setId(result.getInt(3));
                role.setName(result.getString(4));
                user.setRole(role);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
