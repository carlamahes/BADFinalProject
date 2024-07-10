package com.mycompany.training;


import java.io.IOException;
import javafx.fxml.FXML;

public class NetworkingController {

    @FXML
    private void switchToCCNACourse() throws IOException {
        App.setRoot("CCNACourse");
    }

    @FXML
    private void switchToACMPCourse() throws IOException {
        App.setRoot("ACMPCourse");
    }

    
     @FXML
    private void switchToHomepage() throws IOException {
        App.setRoot("homepage");
    }
}