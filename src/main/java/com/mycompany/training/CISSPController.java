package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;

public class CISSPController {

    @FXML
    private void switchToCEHMaterialPart() throws IOException {
        App.setRoot("CEHMaterialPart");
    }
}