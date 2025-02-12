package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnect {

    private Connection connect;

    public dbConnect() {
        try {
            String url = "jdbc:mysql://localhost:3306/sys_db"; 
            String user = "root";
            String password = "";

            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage()); 
            connect = null; 
        }
    }

    public Connection getConnection() {
        return connect; 
    }

    public ResultSet getData(String sql) throws SQLException {
        if (connect == null) {
            throw new SQLException("Connection is null. Database may not be connected."); 
        }
        try (Statement state = connect.createStatement()) { 
            return state.executeQuery(sql);
        } 
    }
}