package com.academy.model.dao;

import com.academy.model.entity.Ticket;

import java.util.List;

public interface TicketDao extends GeneralDao<Ticket> {
    List<Ticket> getByPassportData(String passportData);

    List<Ticket> getByRouteId(Integer routeId);

    Ticket getByOrderId(Integer orderId);
}
