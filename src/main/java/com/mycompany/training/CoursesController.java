package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;

public class CoursesController {

    @FXML
    private void switchToMaterials() throws IOException {
        App.setRoot("Materials");
    }
}