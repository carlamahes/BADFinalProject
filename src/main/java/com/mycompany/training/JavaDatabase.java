package com.mycompany.training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaDatabase extends App {

    @Override
    public void start(Stage primaryStage) {
        // Text fields for input
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");

        // Button to save data
        Button saveButton = new Button();
        saveButton.setText("Sign Up");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                System.out.println("Saving Data: " + username + ", " + email + ", " + password); // Debugging line
                saveData(username, email, password);
            }
        });

        // Button to connect and retrieve data
        Button connectButton = new Button();
        connectButton.setText("Connect to MySQL");
        connectButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                retrieveData();
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(usernameField, emailField, passwordField, saveButton, connectButton);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("IT Academy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void saveData(String username, String email, String password) {
        String insertSQL = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
             
            System.out.println("Connection established"); // Debugging line

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Data saved successfully!");
            } else {
                System.out.println("Failed to save data.");
            }
        } catch (SQLException e) {
            // Print SQL exception information
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
    }

    private void retrieveData() {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
             ResultSet rs = preparedStatement.executeQuery()) {

            System.out.println("Connection established for retrieval"); // Debugging line

            // Loop through resultset
            while (rs.next()) {
                int userId = rs.getInt("userid");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");

                System.out.println("UserID: " + userId + ", Username: " + username + ", Email: " + email + ", Password: " + password);
            }
        } catch (SQLException e) {
            // Print SQL exception information
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Error: " + e.getErrorCode());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
