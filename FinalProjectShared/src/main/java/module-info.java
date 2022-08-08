module com.example.finalprojectshared {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finalprojectshared to javafx.fxml;
    exports com.example.finalprojectshared;
}