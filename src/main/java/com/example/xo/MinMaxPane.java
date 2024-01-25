package com.example.xo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MinMaxPane extends Pane {

//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
    private int mode;

    public MinMaxPane(){
        super();
    }
    public MinMaxPane(int mode){
        this.mode = mode;
    }
    public void start() {


        ComboBox comboBox = new ComboBox<>();
        ObservableList<String> symbols = FXCollections.observableArrayList("X", "O");
        comboBox.setItems(symbols);

        comboBox.setLayoutX(50);
        comboBox.setLayoutY(50);
        Label welcome = new Label("Welcome to هي ذقني اذا فزت XO");
        welcome.setPrefSize(300,30);
        welcome.setLayoutX(380);
        welcome.setLayoutY(20);

        Button start = new Button("Start Game");
        Button nextRound = new Button("Again?");

        nextRound.setLayoutX(50);
        nextRound.setLayoutY(400);
        start.setLayoutX(50);
        start.setLayoutY(300);

        start.setLayoutX(50);
        start.setLayoutY(100);

        Label computerScoreLabel = new Label("Computer Wins: ");
        Label YourScore = new Label("Draws : ");
        Label roundNumberLabel = new Label("Round :");
        Label computerScore = new Label("0");
        Label opScore = new Label("0");
        Label roundNumber = new Label("0");

        computerScoreLabel.setLayoutX(800);
        computerScoreLabel.setLayoutY(140);
        computerScore.setLayoutX(950);
        computerScore.setLayoutY(140);

        YourScore.setLayoutX(800);
        YourScore.setLayoutY(240);
        opScore.setLayoutX(950);
        opScore.setLayoutY(240);

        roundNumber.setLayoutX(950);
        roundNumber.setLayoutY(340);

        roundNumberLabel.setLayoutX(800);
        roundNumberLabel.setLayoutY(340);


        start.setOnAction(e->{
            for (Object o : this.getChildren()){
                if (o instanceof XOInterFace){
                    this.getChildren().remove(o);
                }
            }
            XOInterFace.playable = true;
            computerScore.setText("0"); opScore.setText("0"); roundNumber.setText("0");
            XOInterFace xoInterFace = new XOInterFace(computerScore,opScore,roundNumber,comboBox.getValue());
            xoInterFace.restart();
            xoInterFace.setLayoutX(350);
            xoInterFace.setLayoutY(100);

            if (comboBox.getValue()=="X"){
                xoInterFace.passPlayer('X');
                this.getChildren().add(xoInterFace);
            }
            else if (comboBox.getValue()=="O"){
                xoInterFace.passPlayer('O');
                this.getChildren().add(xoInterFace);
                xoInterFace.makeAIMove();
            }
            nextRound.setOnAction(ex->{
                xoInterFace.clear();
                XOInterFace.playable = true;
                if (comboBox.getValue().equals("O")){
                    xoInterFace.makeAIMove();
                }
            });
        });
        start.getStyleClass().add("Face-Button");
        nextRound.getStyleClass().add("Face-Button");
        this.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        this.getStyleClass().add("styled-Pane");
        this.setPrefSize(1024,512);
        this.getChildren().addAll(comboBox,start,computerScoreLabel,computerScore,YourScore,opScore,nextRound,welcome,roundNumber,roundNumberLabel);
    }
}
