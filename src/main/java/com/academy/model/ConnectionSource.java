package com.academy.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSource {

    public static Connection initConnection() {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream("src/main/resources/aircompany_db.properties")) {
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(prop.getProperty("aircompany_db.url"), prop.getProperty("aircompany_db.user"),
                    prop.getProperty("aircompany_db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
