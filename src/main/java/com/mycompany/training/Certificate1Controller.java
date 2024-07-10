package com.mycompany.training;


import java.io.IOException;
import javafx.fxml.FXML;

public class Certificate1Controller {

    @FXML
    private void switchToHomepage () throws IOException {
        App.setRoot("homepage");
    }
    
}