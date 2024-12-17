package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
//    private static final String URL = "jdbc:mysql://localhost:3307/library_db";
    private static final String LOCAL_URL = "jdbc:mysql://localhost:3307/library_db";
    private static final String DOCKER_URL = "jdbc:mysql://host.docker.internal:3307/library_db";

    private static String dbUrl = System.getProperty("DB_HOST", "localhost").equals("host.docker.internal") ? DOCKER_URL : LOCAL_URL;
    private static final String USER = "root";
    private static final String PASSWORD = "llave";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Database connection error", e);
        }
    }

//    public static void main(String[] args) throws SQLException {
//        getConnection();
//    }
}
