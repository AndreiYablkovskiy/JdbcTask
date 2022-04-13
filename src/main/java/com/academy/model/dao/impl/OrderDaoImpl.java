package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.OrderDao;
import com.academy.model.entity.Order;
import com.academy.model.entity.Role;
import com.academy.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void create(Order order) {
        String sql = "insert into aircompany_db.order (number, order_date, user_id) values(?,?,?)";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getNumber());
            statement.setDate(2, order.getOrderDate());
            statement.setInt(3, order.getUser().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order.id, order.number, order.order_date, order.user_id, user.name, user.role_id, role.name " +
                "FROM aircompany_db.order " +
                "inner join user " +
                "on order.user_id = user.id " +
                "inner join role " +
                "on user.role_id = role.id";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Order order = new Order();
                User user = new User();
                Role role = new Role();
                order.setId(result.getInt(1));
                order.setNumber(result.getInt(2));
                order.setOrderDate(result.getDate(3));
                user.setId(result.getInt(4));
                user.setName(result.getString(5));
                role.setId(result.getInt(6));
                role.setName(result.getString(7));
                user.setRole(role);
                order.setUser(user);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order getById(Integer id) {
        Order order = new Order();
        User user = new User();
        Role role = new Role();
        String sql = "SELECT order.id, order.number, order.order_date, order.user_id, user.name, user.role_id, role.name " +
                "FROM aircompany_db.order " +
                "inner join user " +
                "on order.user_id = user.id " +
                "inner join role " +
                "on user.role_id = role.id " +
                "where order.id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                order.setId(result.getInt(1));
                order.setNumber(result.getInt(2));
                order.setOrderDate(result.getDate(3));
                user.setId(result.getInt(4));
                user.setName(result.getString(5));
                role.setId(result.getInt(6));
                role.setName(result.getString(7));
                user.setRole(role);
                order.setUser(user);
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Order order) {
        String sql = "update aircompany_db.order set number=? where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getNumber());
            statement.setInt(2, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {
        String sql = "delete from aircompany_db.order where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getByUserId(User someUser) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order.id, order.number, order.order_date, order.user_id, user.name, user.role_id, role.name " +
                "FROM aircompany_db.order " +
                "inner join user " +
                "on order.user_id = user.id " +
                "inner join role " +
                "on user.role_id = role.id " +
                "where user.id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, someUser.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Order order = new Order();
                User user = new User();
                Role role = new Role();
                order.setId(result.getInt(1));
                order.setNumber(result.getInt(2));
                order.setOrderDate(result.getDate(3));
                user.setId(result.getInt(4));
                user.setName(result.getString(5));
                role.setId(result.getInt(6));
                role.setName(result.getString(7));
                user.setRole(role);
                order.setUser(user);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order getByNumber(Integer number) {
        Order order = new Order();
        User user = new User();
        Role role = new Role();
        String sql = "SELECT order.id, order.number, order.order_date, order.user_id, user.name, user.role_id, role.name " +
                "FROM aircompany_db.order " +
                "inner join user " +
                "on order.user_id = user.id " +
                "inner join role " +
                "on user.role_id = role.id " +
                "where order.number=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, number);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                order.setId(result.getInt(1));
                order.setNumber(result.getInt(2));
                order.setOrderDate(result.getDate(3));
                user.setId(result.getInt(4));
                user.setName(result.getString(5));
                role.setId(result.getInt(6));
                role.setName(result.getString(7));
                user.setRole(role);
                order.setUser(user);
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
