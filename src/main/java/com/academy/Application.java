package com.academy;

import com.academy.model.dao.OrderDao;
import com.academy.model.dao.impl.OrderDaoImpl;
import com.academy.model.dao.impl.UserDaoImpl;
import com.academy.model.entity.Order;
import com.academy.model.entity.User;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        UserDaoImpl u = new UserDaoImpl();
        User byId = u.getById(1);
        System.out.println(byId.getName());


    }
}

