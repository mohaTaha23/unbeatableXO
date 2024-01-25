package com.example.xo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane mainPane = new Pane();
        Scene scene = new Scene(mainPane,1024,512);
        Button minmax = new Button("Start Game");

        minmax.setLayoutX(440);
        minmax.setLayoutY(240);

        minmax.setPrefSize(144,60);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        mainPane.getStyleClass().add("pane");
        mainPane.getChildren().addAll(minmax);


        minmax.setOnAction(e->{
            mainPane.getChildren().clear();
            MinMaxPane MinMaxPane = new MinMaxPane();
            mainPane.getChildren().add(MinMaxPane);
            scene.getStylesheets().removeAll(this.getClass().getResource("style.css").toExternalForm());
            MinMaxPane.start();
        });
        mainPane.getStyleClass().add("styled-Pane");
        minmax.getStyleClass().add("Face-Button");
        stage.setScene(scene);
        stage.setTitle("play X O");
        stage.show();
    }
}
