package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.OrderDao;
import com.academy.model.entity.Order;
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
            statement.setInt(3, order.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "select * from aircompany_db.order";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getInt(1));
                order.setNumber(result.getInt(2));
                order.setOrderDate(result.getDate(3));
                order.setUserId(result.getInt(4));
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
        String sql = "SELECT * FROM aircompany_db.order where id=?";
        Order order = new Order();

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                order.setId(result.getInt(1));
                order.setNumber(result.getInt(2));
                order.setOrderDate(result.getDate(3));
                order.setUserId(result.getInt(4));
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
    public List<Order> getByUserId(User user) {
        List<Order> orders = new ArrayList<>();
        String sql = "select * from aircompany_db.order where user_id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Order order = new Order();
                order.setId(result.getInt(1));
                order.setNumber(result.getInt(2));
                order.setOrderDate(result.getDate(3));
                order.setUserId(result.getInt(4));
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
        String sql = "SELECT * FROM aircompany_db.order where number=?";
        Order order = new Order();

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, number);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                order.setId(result.getInt(1));
                order.setNumber(result.getInt(2));
                order.setOrderDate(result.getDate(3));
                order.setUserId(result.getInt(4));
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
