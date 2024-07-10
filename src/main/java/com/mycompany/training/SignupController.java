package com.mycompany.training;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class SignupController {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/database1";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";

    private static Connection connection = null;

    @FXML
    private TextField username1; // This is the TextField for the username
    @FXML
    private TextField email; // This is the TextField for the email
    @FXML
    private PasswordField password1; // This is the PasswordField for the password
    @FXML
    private PasswordField password; // This is the PasswordField for password confirmation
    @FXML
    private Button signupButton; // This is the Button for sign up

    @FXML
    private void initialize() {
        // Optional: You can initialize components or listeners here
    }
    
    @FXML
    public void handleSignup(ActionEvent event) {
        String username = username1.getText();
        String emailText = email.getText();
        String passwordText = password1.getText();
        String confirmPasswordText = password.getText();

        if (validateFields(username, emailText, passwordText, confirmPasswordText)) {
            insertIntoDatabase(username, emailText, passwordText);
        }
    }
    
    private boolean validateFields(String username, String email, String password, String confirmPassword) {
        // Check if fields are empty
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Incomplete Fields", "Please fill in all fields.");
            return false;
        }

        // Check if email ends with "@gmail.com"
        if (!email.endsWith("@gmail.com")) {
            showAlert("Invalid Email", "Email must be a Gmail address ending with '@gmail.com'.");
            return false;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            showAlert("Password Mismatch", "Passwords do not match. Please re-enter.");
            return false;
        }

        return true;
    }

    private void insertIntoDatabase(String username, String email, String password) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            }

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();

            showAlert("Signup Successful", "User registered successfully!");
        } catch (SQLException e) {
            showAlert("Database Error", "An error occurred while connecting to the database.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
    private void switchToLogin() throws IOException {
         App.setRoot("login");    
    }
}
