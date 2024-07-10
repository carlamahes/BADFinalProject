package com.mycompany.training;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FinalProjectCISSPController {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/your_database";
private static final String DATABASE_USERNAME = "your_username";
private static final String DATABASE_PASSWORD = "your_password";

    
    private int userId = 1; // Replace with actual user ID logic

    @FXML
    private void switchToSubmitCISSP() throws IOException {
        if (isAllMaterialsCompleted()) {
            App.setRoot("SubmitCISSP");
        } else {
            showAlert("Access Denied", "You need to finish all materials first.");
        }
    }

    @FXML
    private void switchToSyllabus() throws IOException {
        App.setRoot("course1M");
    }

    private boolean isAllMaterialsCompleted() {
        String sql = "SELECT material1_completed, material2_completed, material3_completed FROM user_progress WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("material1_completed") && rs.getBoolean("material2_completed") && rs.getBoolean("material3_completed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
