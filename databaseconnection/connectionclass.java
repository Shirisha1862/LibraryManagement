package databaseconnection;

import java.sql.*;

public class connectionclass {
    public static Connection conn;

    private static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SIRI", "root", "12345678");
        System.out.println("Connection Successful!");
        return conn;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            return createConnection();  // Create a connection if it doesn't exist
        }
        return conn;
    }

    // 👉 Main method to test connection
    public static void main(String[] args) {
        try {
            getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
