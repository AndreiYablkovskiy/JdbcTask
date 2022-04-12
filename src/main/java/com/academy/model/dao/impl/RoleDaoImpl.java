package com.academy.model.dao.impl;

import com.academy.model.ConnectionSource;
import com.academy.model.dao.RoleDao;
import com.academy.model.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    @Override
    public void create(Role role) {
        String sql = "insert into aircompany_db.role (name) values(?)";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        String sql = "select * from aircompany_db.role";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Role role = new Role();
                role.setId(result.getInt(1));
                role.setName(result.getString(2));
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role getById(Integer id) {
        String sql = "SELECT * FROM aircompany_db.role where id=?";
        Role role = new Role();

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                role.setId(result.getInt(1));
                role.setName(result.getString(2));
                return role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Role role) {
        String sql = "update aircompany_db.role set name=? where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, role.getName());
            statement.setInt(2, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Role role) {
        String sql = "delete from aircompany_db.role where id=?";

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, role.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role getByName(String name) {
        String sql = "SELECT * FROM aircompany_db.role where name=?";
        Role roleToReturn = new Role();

        try (Connection connection = ConnectionSource.initConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                roleToReturn.setId(result.getInt(1));
                roleToReturn.setName(result.getString(2));
                return roleToReturn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
