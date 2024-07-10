package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class CNNAMaterialController {

    private boolean material1Visited = true; // Material 1 is always accessible initially
    private boolean material2Visited = false;
    private boolean material3Visited = false;
    private boolean material4Visited = false;

    @FXML
    private void switchToMaterial1() throws IOException {
        if (material1Visited) {
            App.setRoot("Material1");
        } else {
            showMaterialNotAccessibleAlert();
        }
    }

    @FXML
    private void switchToMaterial2() throws IOException {
        if (material1Visited && material2Visited) {
            App.setRoot("Material2");
        } else {
            showMaterialNotAccessibleAlert();
        }
    }

    @FXML
    private void switchToMaterial3() throws IOException {
        if (material1Visited && material2Visited && material3Visited) {
            App.setRoot("Material3");
        } else {
            showMaterialNotAccessibleAlert();
        }
    }

    @FXML
    private void switchToMaterial4() throws IOException {
        if (material1Visited && material2Visited && material3Visited && material4Visited) {
            App.setRoot("Material4");
        } else {
            showMaterialNotAccessibleAlert();
        }
    }

    @FXML
    private void switchToCCNACourse() throws IOException {
        App.setRoot("CCNACourse");
    }

    private void showMaterialNotAccessibleAlert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Material Not Accessible");
        alert.setHeaderText(null);
        alert.setContentText("Please access the previous material first.");
        alert.showAndWait();
    }
}
