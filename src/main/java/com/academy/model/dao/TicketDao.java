package com.academy.model.dao;

import com.academy.model.entity.Order;
import com.academy.model.entity.Route;
import com.academy.model.entity.Ticket;

import java.util.List;

public interface TicketDao extends GeneralDao<Ticket> {
    List<Ticket> getByPassportData(String passportData);

    List<Ticket> getByRouteId(Route route);

    Ticket getByOrderId(Order order);
}
