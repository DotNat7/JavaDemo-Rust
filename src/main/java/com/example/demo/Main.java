package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {


    static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
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
}



        /*window = primaryStage;

        Label label1 = new Label("Welcome to the first scene!");
        Button button1 = new Button("Go to the second scene!");
        button1.setOnAction(e -> window.setScene(scene2));

        // Layout1
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        //Button2
        Button button2 = new Button("Go back to the first scene!");
        button2.setOnAction(e -> window.setScene(scene1));

        // Layout2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 300, 200);

        window.setScene(scene1);
        window.setTitle("Welcome to the first scene!");
        window.show();*/





/*primaryStage.setTitle("Title of the window");

        button = new Button();
        button.setText("Click me");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 320, 240);
        primaryStage.setScene(scene);
        primaryStage.show();*/
