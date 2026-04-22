package org.com.example.javainterface.DTBClasses;
import java.sql.*;


public class DTBConfig {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/EngenTech";
        String user = "root";
        String password = "#1307";

        return DriverManager.getConnection(url, user, password);
    }

}
