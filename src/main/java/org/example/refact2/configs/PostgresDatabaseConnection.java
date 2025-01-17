package org.example.refact2.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDatabaseConnection implements Database {

    private final Connection connection;

    public PostgresDatabaseConnection() throws SQLException {
        this.connection = createConnection();
    }

    private Connection createConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String user = "myuser";
        String password = "mypassword";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
