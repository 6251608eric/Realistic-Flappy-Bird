module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.xml.dom;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}