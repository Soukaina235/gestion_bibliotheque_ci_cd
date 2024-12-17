package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
////    private static final String URL = "jdbc:mysql://localhost:3307/library_db";
//    private static final String LOCAL_URL = "jdbc:mysql://localhost:3307/library_db";
//    private static final String DOCKER_URL = "jdbc:mysql://host.docker.internal:3307/library_db";
//
//    private static String dbUrl = System.getProperty("DB_HOST", "localhost").equals("host.docker.internal") ? DOCKER_URL : LOCAL_URL;
//    private static final String USER = "root";
//    private static final String PASSWORD = "llave";

    private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://localhost:3307/library_db";
    private static final String USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "llave";


    public static Connection getConnection() throws SQLException {
        System.out.println("url " + URL);
        System.out.println("user " + USER);
        System.out.println("password " + PASSWORD);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Database connection error", e);
        }
    }

//    public static void main(String[] args) throws SQLException {
//        getConnection();
//    }
}
