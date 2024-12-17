package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:mysql://mysql-337f9109-soukaina-e199.a.aivencloud.com:19094/library_db";
    private static final String USER = "bibliotheque-jenkins";
    private static final String PASSWORD = "AVNS_polljiBexINw-ILiAUI";


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Database connection error", e);
        }
    }
}
