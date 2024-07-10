package com.mycompany.training;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Course1Controller {

    @FXML
    private void switchToCourse1M() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to enroll?");
        
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                try {
                    App.setRoot("course1M"); 
                } catch (IOException e) {
                    e.printStackTrace();               
                }
            }
            
        });
    }
    
    @FXML
    private void switchToCybersecurity () throws IOException {
        App.setRoot("cybersecurity");
    }
    
    @FXML
    private void switchToparticipants () throws IOException {
        App.setRoot("Participants");
    }
}