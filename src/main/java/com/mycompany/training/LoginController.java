package com.mycompany.training;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/database1";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    
    private static Connection connection = null;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button primaryButton;

    @FXML
    private void initialize() {
        // Set an action handler for the primary button
        primaryButton.setOnAction(event -> {
            try {
                handleLogin(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void switchToSignup() throws IOException {
        App.setRoot("signup");
    }

    @FXML
    private void switchToHomepage() throws IOException {
        App.setRoot("homepage");
    }

    private void handleLogin(ActionEvent event) throws IOException {
        String user = username.getText();
        String pass = password.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            showAlert("Error", "Username and password must be filled.");
        } else {
            if (validateLogin(user, pass)) {
                switchToHomepage();
            } else {
                showAlert("Error", "Invalid username or password.");
            }
        }
    }

    private boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            }

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true; // User found
            }
        } catch (SQLException e) {
            showAlert("Database Error", "An error occurred while connecting to the database.");
            e.printStackTrace();
        }

        return false; // User not found
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
