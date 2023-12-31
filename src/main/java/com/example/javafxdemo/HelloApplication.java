package com.example.javafxdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 640);
        stage.setTitle("Word Frequency");
        stage.setScene(scene);
        stage.show();
    }

    // Testing
    // Address: "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm"
    // Table Name: Chapter


    public static void main(String[] args) {
        launch();
    }
}