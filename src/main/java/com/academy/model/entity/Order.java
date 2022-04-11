package com.academy.model.entity;

import java.sql.Date;

public class Order {
    private Integer id;
    private Integer number;
    private Date orderDate;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer usersId) {
        this.userId = userId;
    }
}
