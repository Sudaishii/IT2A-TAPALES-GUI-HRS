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
            String url = "jdbc:mysql://localhost:3306/sys_db"; // Put URL, username, password in separate variables for clarity
            String user = "root";
            String password = "";

            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage()); // Use System.err for errors
            connect = null; // Explicitly set connect to null on failure
        }
    }

    public Connection getConnection() {
        return connect; // Now safe to return, might be null
    }

    public ResultSet getData(String sql) throws SQLException {
        if (connect == null) {
            throw new SQLException("Connection is null. Database may not be connected."); // Throw exception early
        }
        try (Statement state = connect.createStatement()) { // Try-with-resources for Statement
            return state.executeQuery(sql);
        } // Statement is automatically closed here
    }
}