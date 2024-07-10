package com.mycompany.training;


import java.io.IOException;
import javafx.fxml.FXML;

public class CybersecurityController {

    @FXML
    private void switchTocourse1() throws IOException {
        App.setRoot("course1");
    }
    
     @FXML
    private void switchToCertifiedEthicalHacker() throws IOException {
        App.setRoot("CertifiedEthicalHacker");
    }
    
     @FXML
    private void switchToHomepage() throws IOException {
        App.setRoot("homepage");
    }
}