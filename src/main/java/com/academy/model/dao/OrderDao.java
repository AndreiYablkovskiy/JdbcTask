package com.academy.model.dao;

import com.academy.model.entity.Order;
import com.academy.model.entity.User;

import java.util.List;

public interface OrderDao extends GeneralDao<Order> {
    List<Order> getByUserId(User user);

    Order getByNumber(Integer number);
}
