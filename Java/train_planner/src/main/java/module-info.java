//module com.example.oop_projekt {
module oop_projekt {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;  // PRIDAL SOM JA
    requires org.postgresql.jdbc;


    opens gui to javafx.fxml;
    exports gui;
}