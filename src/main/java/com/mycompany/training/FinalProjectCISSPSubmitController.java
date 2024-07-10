package com.mycompany.training;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class FinalProjectCISSPSubmitController {

    @FXML
    private Button submit;

    // Database information
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/database1";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";

    // Variables to hold user_id and project_id
    private int user_id;
    private int project_id;

    @FXML
    private void initialize() {
        // Fetch user_id and project_id from the database
        user_id = fetchLatestUserId();
        project_id = fetchLatestProjectId();

        // Log to check fetched user_id and project_id values
        System.out.println("Fetched User ID: " + user_id);
        System.out.println("Fetched Project ID: " + project_id);

        submit.setDisable(false);

        // Event listener for the submit button
        submit.setOnAction(this::handleSubmit);

        // Log to ensure initialize method is called
        System.out.println("Initialize method called");
    }

    @FXML
    private void switchToCertificate1() throws IOException {
        App.setRoot("certificate1");
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        String insertSql = "INSERT INTO submissions (user_id, project_id, submission_date) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

            // Prepare SQL statement
            pstmt.setInt(1, user_id);
            pstmt.setInt(2, project_id);
            pstmt.setObject(3, LocalDateTime.now()); // Use current time as submission_date

            // Execute statement
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                showAlert("Submission Successful", "Submission has been saved successfully.");
                try {
                    switchToCourseEnroll();
                } catch (IOException ex) {
                    Logger.getLogger(FinalProjectCISSPSubmitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                showAlert("Submission Failed", "Failed to save submission. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "An error occurred while accessing the database: " + e.getMessage());
        }
    }

    private int fetchLatestUserId() {
        String query = "SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return an invalid ID if not found
    }

    private int fetchLatestProjectId() {
        String query = "SELECT project_id FROM projects ORDER BY project_id DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("project_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return an invalid ID if not found
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void switchToCourseEnroll() throws IOException {
        App.setRoot("course1");
    }
}
