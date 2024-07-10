package com.mycompany.training;

import java.io.IOException;
import java.math.BigDecimal;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PremiumPlanController {

    @FXML
    private void switchToCertifiedEthic() throws IOException {
        App.setRoot("CertifiedEthicalHacker");
    }
    
    @FXML
    private void switchToMonthly() throws IOException {
        App.setRoot("MonthlyPlan");
    }

    @FXML
    private void showPaymentPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Required");
        alert.setHeaderText("Payment Process");
        alert.setContentText("You need to pay for the premium plan. Click 'Pay' to proceed.");
        
        ButtonType buttonTypePay = new ButtonType("Pay");
        ButtonType buttonTypeCancel = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(buttonTypePay, buttonTypeCancel);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypePay) {
                showVirtualAccountPopup();
            }
        });
    }

    private void showVirtualAccountPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Virtual Account");
        alert.setHeaderText("Virtual Account Details");
        alert.setContentText("Your virtual account number is 123456789. Click 'Pay' to confirm.");

        ButtonType buttonTypePay = new ButtonType("Pay");
        ButtonType buttonTypeCancel = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(buttonTypePay, buttonTypeCancel);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypePay) {
                savePaymentInDatabase();
                showSuccessMessage();
            }
        });
    }

    private void savePaymentInDatabase() {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "your_username";
        String password = "your_password";

        String sql = "INSERT INTO payment (paymentId, paymentType, price, user_id) VALUES (?, ?, ?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "Premium Plan");
            pstmt.setBigDecimal(2, new BigDecimal("99.99")); // Adjust price as necessary
            pstmt.setInt(3, 1); // Assuming user_id is 1. Change as per your logic.

            pstmt.executeUpdate();

            // Set payment status to true
            PaymentStatusController.setPaid(true);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Successful");
        alert.setHeaderText(null);
        alert.setContentText("Your payment was successful. You now have access to the premium content.");

        alert.showAndWait().ifPresent(response -> {
            try {
                App.setRoot("PremiumContent"); // Change to the actual FXML file for the premium content
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

   
}
