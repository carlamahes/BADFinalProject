package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CertifiedEthicController {

    @FXML
    private void switchToCybersecurity() throws IOException {
        App.setRoot("cybersecurity");
    }

    @FXML
    private void switchToPremiumPlan() throws IOException {
        App.setRoot("PremiumPlan");
    }

    @FXML
    private void showPremiumPlanPopup() {
        if (PaymentStatusController.isPaid()) {
            try {
                App.setRoot("PremiumContent"); // Change to the actual FXML file for the premium content
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premium Plan Required");
            alert.setHeaderText(null);
            alert.setContentText("To access this content, you need to enroll in our Premium Plan.");

            ButtonType buttonTypeOk = new ButtonType("OK");
            ButtonType buttonTypePremiumPlan = new ButtonType("Premium Plan");

            alert.getButtonTypes().setAll(buttonTypeOk, buttonTypePremiumPlan);

            alert.showAndWait().ifPresent(response -> {
                if (response == buttonTypePremiumPlan) {
                    try {
                        switchToPremiumPlan();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
