package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;

public class CEHMaterial2Controller {

    @FXML
    private void switchToCEHMaterial3() throws IOException {
        App.setRoot("CEHMaterial3");
    }
    
    @FXML
    private void switchToCEHMaterialPart() throws IOException {
        App.setRoot("CEHMaterialPart");
    }
}