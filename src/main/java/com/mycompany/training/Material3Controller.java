package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;

public class Material3Controller {

    @FXML
    private void switchTocourse1M() throws IOException {
        App.setRoot("course1M");
    }

    @FXML
    private void switchToFinalProjectCISSP() throws IOException {
        App.setRoot("CISSPFinalProjectSubmit");
    }
}
