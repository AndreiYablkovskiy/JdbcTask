package com.academy.model.dao;

import com.academy.model.entity.Route;
import com.academy.model.entity.Ticket;

import java.util.List;

public interface TicketDao {
    void create(Ticket ticket);

    List<Ticket> getAll();

    Ticket getById(Integer id);

    void update(Ticket ticket);

    void delete(Ticket ticket);

    String getPassportData(Ticket ticket);

    Integer getRouteId(Ticket ticket);

    Integer getOrderId(Ticket ticket);
}
