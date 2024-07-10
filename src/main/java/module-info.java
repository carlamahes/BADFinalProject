module com.mycompany.training {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.training to javafx.fxml;
    exports com.mycompany.training;
}
