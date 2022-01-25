module com.example.jdbcc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.jdbcc to javafx.fxml;
    exports com.example.jdbcc;
}