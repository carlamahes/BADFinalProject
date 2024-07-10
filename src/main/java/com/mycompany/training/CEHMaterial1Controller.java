package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;

public class CEHMaterial1Controller {

    @FXML
    private void switchToCEHMaterialPart() throws IOException {
        App.setRoot("CEHMaterialPart");
    }
    
    @FXML
    private void switchToCEHMaterial2() throws IOException {
        App.setRoot("CEHMaterial2");
    }
}