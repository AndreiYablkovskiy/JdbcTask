package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.TicketDao;
import com.academy.model.entity.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    @Override
    public void create(Ticket ticket) {
        String sql = "insert into aircompany_db.ticket (route_id, order_id, passport_data) values(?,?,?)";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ticket.getRouteId());
            statement.setInt(2, ticket.getOrderId());
            statement.setString(3, ticket.getPassportData());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "select * from aircompany_db.ticket";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(result.getInt(1));
                ticket.setRouteId(result.getInt(2));
                ticket.setOrderId(result.getInt(3));
                ticket.setPassportData(result.getString(4));
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket getById(Integer id) {
        String sql = "SELECT * FROM aircompany_db.ticket where id=?";
        Ticket ticket = new Ticket();

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ticket.setId(result.getInt(1));
                ticket.setRouteId(result.getInt(2));
                ticket.setOrderId(result.getInt(3));
                ticket.setPassportData(result.getString(4));
                return ticket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Ticket ticket) {
        String sql = "update aircompany_db.airplane set passport_data=? where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, ticket.getPassportData());
            statement.setInt(2, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Ticket ticket) {
        String sql = "delete from aircompany_db.ticket where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> getByPassportData(String passportData) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM aircompany_db.ticket where passport_data=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, passportData);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Ticket newTicket = new Ticket();
                newTicket.setId(result.getInt(1));
                newTicket.setRouteId(result.getInt(2));
                newTicket.setOrderId(result.getInt(3));
                newTicket.setPassportData(result.getString(4));
                tickets.add(newTicket);
                return tickets;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ticket> getByRouteId(Integer routeId) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM aircompany_db.ticket where route_id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, routeId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Ticket newTicket = new Ticket();
                newTicket.setId(result.getInt(1));
                newTicket.setRouteId(result.getInt(2));
                newTicket.setOrderId(result.getInt(3));
                newTicket.setPassportData(result.getString(4));
                tickets.add(newTicket);
                return tickets;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket getByOrderId(Integer orderId) {
        Ticket newTicket = new Ticket();
        String sql = "SELECT * FROM aircompany_db.ticket where order_id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                newTicket.setId(result.getInt(1));
                newTicket.setRouteId(result.getInt(2));
                newTicket.setOrderId(result.getInt(3));
                newTicket.setPassportData(result.getString(4));
                return newTicket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
