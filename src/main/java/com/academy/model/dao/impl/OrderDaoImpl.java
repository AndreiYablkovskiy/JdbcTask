package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.OrderDao;
import com.academy.model.entity.Order;

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
    public Integer getUserId(Integer orderId) {
        String sql = "SELECT user_id FROM aircompany_db.order where id=?";
        Integer userId;

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                userId = result.getInt(1);
                return userId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order getOrderByNumber(Integer number) {
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

    @Override
    public Date getDateByOrderId(Integer orderId) {
        String sql = "SELECT order_date FROM aircompany_db.order where id=?";
        Date orderDate;

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                orderDate = result.getDate(1);
                return orderDate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
