module com.example.javafxdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.javafxdemo to javafx.fxml;
    exports com.example.javafxdemo;
}