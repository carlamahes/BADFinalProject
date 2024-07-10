package com.mycompany.training;

import java.io.IOException;
import javafx.fxml.FXML;

public class Material2Controller {
    
    

    @FXML
    private void switchToMaterial3() throws IOException {
        // Menandai Material 2 sebagai selesai
        Course1MController.setMaterialCompleted("material2_completed");
        
        // Pindah ke Material 3
        App.setRoot("Material3");
    }

    @FXML
    private void switchTocourse1M() throws IOException {
        App.setRoot("course1M");
    }
}
