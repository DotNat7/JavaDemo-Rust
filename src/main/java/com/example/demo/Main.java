package com.example.demo;

import com.example.demo.util.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        HibernateUtil.getSessionFactory().openSession();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Test architektury");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        HibernateUtil.shutdown();
    }
}