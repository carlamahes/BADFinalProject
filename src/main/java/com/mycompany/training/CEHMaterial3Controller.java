package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;

public class CEHMaterial3Controller {

    @FXML
    private void switchToCEHMaterialPart() throws IOException {
        App.setRoot("CEHMaterialPart");
    }
    
    @FXML
    private void switchToCEHFinalProject() throws IOException {
        App.setRoot("CEHFinalProject");
    }
}