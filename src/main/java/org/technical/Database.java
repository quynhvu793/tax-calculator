package org.technical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:sqlite:kindergarten.db";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Kết nối DB thành công!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Kết nối DB thất bại: " + e.getMessage());
            return null;
        }
    }
}
