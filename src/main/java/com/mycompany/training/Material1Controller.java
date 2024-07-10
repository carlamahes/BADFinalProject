package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;

public class Material1Controller {

    @FXML
    private void switchToMaterial2() throws IOException {
        App.setRoot("Material2");
        Course1MController.setMaterialCompleted("material1_completed"); // Memanggil metode untuk menandai material 1 selesai
    }

    @FXML
    private void switchTocourse1M() throws IOException {
        App.setRoot("course1M");
    }
}
