package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class showSuccessMessage {

    private void showSuccessMessage() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Payment Successful");
    alert.setHeaderText(null);
    alert.setContentText("Your payment was successful. You now have access to the premium content.");

    alert.showAndWait().ifPresent(response -> {
        try {
            App.setRoot("CEHMaterialPartController"); // Change to the actual FXML file for the premium content
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
}

}
