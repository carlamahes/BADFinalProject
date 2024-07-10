package com.mycompany.training;


import java.io.IOException;
import javafx.fxml.FXML;

public class Participants {

    @FXML
    private void switchTocourse1 () throws IOException {
        App.setRoot("course1");
    }
}