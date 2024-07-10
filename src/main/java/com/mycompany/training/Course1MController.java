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

public class Course1MController {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";

    private int userId = 1; // Untuk sementara, ganti dengan logika pengguna yang sebenarnya

    @FXML
    private void switchTocybersecurity() throws IOException {
        App.setRoot("Cybersecurity");
    }

    @FXML
    private void switchTomaterial1() throws IOException {
        App.setRoot("material1");
    }

    @FXML
    private void switchToMaterial2() throws IOException {
        if (isMaterialCompleted("material1_completed")) {
            App.setRoot("Material2");
            setMaterialCompleted("material2_completed"); // Menandai material 2 selesai saat diakses
        } else {
            showAlert("Access Denied", "Anda perlu menyelesaikan Material 1 terlebih dahulu.");
        }
    }

    @FXML
    private void switchToMaterial3() throws IOException {
        if (isMaterialCompleted("material1_completed") && isMaterialCompleted("material2_completed")) {
            App.setRoot("Material3");
            setMaterialCompleted("material3_completed"); // Menandai material 3 selesai saat diakses
        } else {
            showAlert("Access Denied", "Anda perlu menyelesaikan Material 1 dan Material 2 terlebih dahulu.");
        }
    }

    private boolean isMaterialCompleted(String material) {
    // Check if material is already marked as completed in user_progress table
    String selectSql = "SELECT " + material + " FROM user_progress WHERE user_id = ?";
    String updateSql = "UPDATE user_progress SET " + material + " = TRUE WHERE user_id = ?";
    
    try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)) {
        // Check if material is completed
        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            selectStmt.setInt(1, userId);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                boolean isCompleted = rs.getBoolean(material); // Return current status from database
                System.out.println("Material " + material + " completion status: " + isCompleted); // Debugging statement
                return isCompleted;
            }
        }
        
        // If material is not completed, mark it as completed in the database
        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setInt(1, userId);
            updateStmt.executeUpdate();
        }
        
        return true; // Material is now marked as completed
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return false; // Default to false in case of error
}

    public static void setMaterialCompleted(String material) {
        String sql = "UPDATE user_progress SET " + material + " = TRUE WHERE user_id = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, 1); // Mengasumsikan userId saat ini adalah 1 untuk sementara
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
