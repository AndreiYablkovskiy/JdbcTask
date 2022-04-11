package com.academy.model.dao;

import com.academy.model.entity.Order;

import java.sql.Date;
import java.util.List;

public interface OrderDao {
    void create(Order order);

    List<Order> getAll();

    Order getById(Integer id);

    void update(Order order);

    void delete(Order order);

    Integer getUserId(Integer orderId);

    Order getOrderByNumber(Integer number);

    Date getDateByOrderId(Integer orderId);
}
