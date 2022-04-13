package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.TicketDao;
import com.academy.model.entity.*;

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
            statement.setInt(1, ticket.getRoute().getId());
            statement.setInt(2, ticket.getOrder().getId());
            statement.setString(3, ticket.getPassportData());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM aircompany_db.ticket " +
                "inner join route " +
                "on ticket.route_id = route.id " +
                "inner join aircompany_db.order " +
                "on ticket.order_id = order.id " +
                "inner join user " +
                "on order.user_id = user.id " +
                "inner join role " +
                "on user.role_id = role.id";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Ticket ticket = new Ticket();
                Route route = new Route();
                Order order = new Order();
                User user = new User();
                Role role = new Role();
                ticket.setId(result.getInt("id"));
                ticket.setPassportData(result.getString("passport_data"));
                route.setId(result.getInt("route_id"));
                route.setDepartureId(result.getInt("departure_id"));
                route.setArrivalId(result.getInt("arrival_id"));
                order.setId(result.getInt("order_id"));
                order.setNumber(result.getInt("number"));
                order.setOrderDate(result.getDate("order_date"));
                user.setId(result.getInt("user_id"));
                user.setName(result.getString(13));
                role.setId(result.getInt("role_id"));
                role.setName(result.getString(16));
                user.setRole(role);
                order.setUser(user);
                ticket.setRoute(route);
                ticket.setOrder(order);
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
        Ticket ticket = new Ticket();
        String sql = "SELECT * " +
                "FROM aircompany_db.ticket " +
                "inner join route " +
                "on ticket.route_id = route.id " +
                "inner join aircompany_db.order " +
                "on ticket.order_id = order.id " +
                "inner join user " +
                "on order.user_id = user.id " +
                "inner join role " +
                "on user.role_id = role.id " +
                "where ticket.id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Route route = new Route();
                Order order = new Order();
                User user = new User();
                Role role = new Role();
                ticket.setId(result.getInt("id"));
                ticket.setPassportData(result.getString("passport_data"));
                route.setId(result.getInt("route_id"));
                route.setDepartureId(result.getInt("departure_id"));
                route.setArrivalId(result.getInt("arrival_id"));
                order.setId(result.getInt("order_id"));
                order.setNumber(result.getInt("number"));
                order.setOrderDate(result.getDate("order_date"));
                user.setId(result.getInt("user_id"));
                user.setName(result.getString(13));
                role.setId(result.getInt("role_id"));
                role.setName(result.getString(16));
                user.setRole(role);
                order.setUser(user);
                ticket.setRoute(route);
                ticket.setOrder(order);
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
        String sql = "SELECT * " +
                "FROM aircompany_db.ticket " +
                "inner join route " +
                "on ticket.route_id = route.id " +
                "inner join aircompany_db.order " +
                "on ticket.order_id = order.id " +
                "inner join user " +
                "on order.user_id = user.id " +
                "inner join role " +
                "on user.role_id = role.id " +
                "where ticket.passport_data=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, passportData);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Ticket ticket = new Ticket();
                Route route = new Route();
                Order order = new Order();
                User user = new User();
                Role role = new Role();
                ticket.setId(result.getInt("id"));
                ticket.setPassportData(result.getString("passport_data"));
                route.setId(result.getInt("route_id"));
                route.setDepartureId(result.getInt("departure_id"));
                route.setArrivalId(result.getInt("arrival_id"));
                order.setId(result.getInt("order_id"));
                order.setNumber(result.getInt("number"));
                order.setOrderDate(result.getDate("order_date"));
                user.setId(result.getInt("user_id"));
                user.setName(result.getString(13));
                role.setId(result.getInt("role_id"));
                role.setName(result.getString(16));
                user.setRole(role);
                order.setUser(user);
                ticket.setRoute(route);
                ticket.setOrder(order);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ticket> getByRouteId(Route someRoute) {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM aircompany_db.ticket " +
                "inner join route " +
                "on ticket.route_id = route.id " +
                "inner join aircompany_db.order " +
                "on ticket.order_id = order.id " +
                "inner join user " +
                "on order.user_id = user.id " +
                "inner join role " +
                "on user.role_id = role.id " +
                "where route.id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, someRoute.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Ticket ticket = new Ticket();
                Route route = new Route();
                Order order = new Order();
                User user = new User();
                Role role = new Role();
                ticket.setId(result.getInt("id"));
                ticket.setPassportData(result.getString("passport_data"));
                route.setId(result.getInt("route_id"));
                route.setDepartureId(result.getInt("departure_id"));
                route.setArrivalId(result.getInt("arrival_id"));
                order.setId(result.getInt("order_id"));
                order.setNumber(result.getInt("number"));
                order.setOrderDate(result.getDate("order_date"));
                user.setId(result.getInt("user_id"));
                user.setName(result.getString(13));
                role.setId(result.getInt("role_id"));
                role.setName(result.getString(16));
                user.setRole(role);
                order.setUser(user);
                ticket.setRoute(route);
                ticket.setOrder(order);
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket getByOrderId(Order someOrder) {
        Ticket ticket = new Ticket();
        String sql = "SELECT * FROM aircompany_db.ticket where order_id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, someOrder.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Route route = new Route();
                Order order = new Order();
                User user = new User();
                Role role = new Role();
                ticket.setId(result.getInt("id"));
                ticket.setPassportData(result.getString("passport_data"));
                route.setId(result.getInt("route_id"));
                route.setDepartureId(result.getInt("departure_id"));
                route.setArrivalId(result.getInt("arrival_id"));
                order.setId(result.getInt("order_id"));
                order.setNumber(result.getInt("number"));
                order.setOrderDate(result.getDate("order_date"));
                user.setId(result.getInt("user_id"));
                user.setName(result.getString(13));
                role.setId(result.getInt("role_id"));
                role.setName(result.getString(16));
                user.setRole(role);
                order.setUser(user);
                ticket.setRoute(route);
                ticket.setOrder(order);
                return ticket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
