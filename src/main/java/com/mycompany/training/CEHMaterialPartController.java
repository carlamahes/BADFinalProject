package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;

public class CEHMaterialPartController {

    @FXML
    private void switchToCEHMaterial1() throws IOException {
        App.setRoot("CEHMaterial1");
    }
    
    @FXML
    private void switchToCEHMaterial2() throws IOException {
        App.setRoot("CEHMaterial2");
    }
    
    @FXML
    private void switchToCEHMaterial3() throws IOException {
        App.setRoot("CEHMaterial3");
    }
    
    @FXML
    private void switchToCEHMaterial4() throws IOException {
        App.setRoot("CEHMaterial4");
    }
    
}