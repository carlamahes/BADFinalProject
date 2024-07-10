package com.mycompany.training;


import java.io.IOException;
import javafx.fxml.FXML;

public class HomepageController {

    @FXML
    private void switchToCybersecurity() throws IOException {
        App.setRoot("cybersecurity");
    }
    
     @FXML
    private void switchToNetworking() throws IOException {
        App.setRoot("Networking");
    }
}