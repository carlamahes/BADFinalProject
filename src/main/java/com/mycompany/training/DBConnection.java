package com.mycompany.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * 
 */
public class DBConnection {
    

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/database1";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                Statement stmt = connection.createStatement();
                stmt.executeQuery("SELECT * FROM users");
                
                
            }
        } catch (SQLException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Database Connection Error");
            alert.setHeaderText("An error occurred while connecting to the database.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            // print SQL exception information
            System.out.println(e);
        }

        return connection;
    }
}
