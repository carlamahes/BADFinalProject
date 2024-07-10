package com.mycompany.training;


import java.io.IOException;
import javafx.fxml.FXML;

public class Validation2Controller {

    @FXML
    private void switchToCourse1M() throws IOException {
        App.setRoot("course1M");
    }
    
    @FXML
    private void switchToCourse1() throws IOException {
        App.setRoot("course1");
    }
}