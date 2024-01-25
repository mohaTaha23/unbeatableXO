package com.example.xo;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class XOInterFace extends GridPane {

    private char [][] xoBoard ;
    private char player =' ';

    private char computer = ' ';

    public static int computerScore =0;

    public static int draw = 0;

    public static boolean playable = true;

    private Label coScoreDis ;
    private Label drawsCount;

    private Label roundNumber;

    public XOInterFace ( Label coScoreDis,Label opScoreDis,Label roundNumber,Object v){
        v = (String)v;
        player=((String) v).charAt(0);
        this.coScoreDis=coScoreDis;
        this.drawsCount = opScoreDis;
        this.roundNumber = roundNumber;
        xoBoard = new char[3][3];
        for (int i =0; i< 3 ; i++){
            for (int j=0 ; j<3 ; j++){
                xoBoard[i][j] =' ';
                this.add(new MyButton(player),i,j);
                this.getElement(j,i).getStyleClass().add("non-Button");
            }
        }
        this.setPrefSize(300,300);
    }
    public void passPlayer(char player){
        if (player =='X') {
            this.player ='X'; this.computer = 'O';
            helper.computer = 'O'; helper.opponent = 'X';
        }
        else {
            this.player='O'; this.computer='X';
            helper.computer = 'X'; helper.opponent = 'O';
        }
    }

    public void clear() {
        for (Object o : this.getChildren()){
            ((MyButton)o).setText(" ");
            ((MyButton)o).getStyleClass().removeAll("winner-Button");
            ((MyButton)o).getStyleClass().add("non-Button");
        }
        for (int i =0; i< 3 ; i++){
            for (int j=0 ; j<3 ; j++) {
                xoBoard[i][j] = ' ';
            }
        }
    }


    class MyButton extends Button {
        char player ;
        public MyButton(char a){
            this.player= a;
            this.setText(" ");
            this.setPrefSize(100,100);
            this.setOnAction(e->{
                if (this.getText().equals(" ")&&playable)
                    play();
            });
        }

        private void play() {
            this.setText(player+"");
            updateGui();
            if (!helper.isMovesLeft(xoBoard)){
                draw++;
                playable = false;
                drawsCount.setText(draw+"");
                roundNumber.setText((draw+computerScore)+"");
                return;
            }
            makeAIMove();
            if (won()){
                computerScore++;
                playable=false;
                coScoreDis.setText(computerScore+"");
                roundNumber.setText((draw+computerScore)+"");
            }
        }
    }

     void makeAIMove() {
        Move move =helper.findBestMove(xoBoard);
        xoBoard[move.row][move.col] = computer;
        updateButtons();
    }

    private void updateButtons() {
        for (int i =0; i< 3 ; i++) {
            for (int j = 0; j < 3; j++) {
                (getElement(i,j)).setText(xoBoard[i][j] +"");
            }
        }
    }

    private void updateGui() {
        for (int i =0; i< 3 ; i++) {
            for (int j = 0; j < 3; j++) {
                xoBoard[i][j] = (getElement(i,j)).getText().charAt(0);
            }
        }
    }
    private MyButton getElement(int row, int column) {
        for (Node node : this.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return (MyButton) node;
            }
        }
        return null;
    }
    private boolean won(){
        for (int i =0;i<3;i++){
            if (xoBoard[i][0] == computer && xoBoard[i][1] == computer && xoBoard[i][2] == computer ){
                this.getElement(i,0).getStyleClass().removeAll("non-Button");
                this.getElement(i,1).getStyleClass().removeAll("non-Button");
                this.getElement(i,2).getStyleClass().removeAll("non-Button");
                this.getElement(i,0).getStyleClass().add("winner-Button");
                this.getElement(i,1).getStyleClass().add("winner-Button");
                this.getElement(i,2).getStyleClass().add("winner-Button");
                return true;
            }
            else if (xoBoard[0][i] == computer && xoBoard[1][i] == computer && xoBoard[2][i] == computer ){
                this.getElement(0,i).getStyleClass().removeAll("non-Button");
                this.getElement(1,i).getStyleClass().removeAll("non-Button");
                this.getElement(2,i).getStyleClass().removeAll("non-Button");
                this.getElement(0,i).getStyleClass().add("winner-Button");
                this.getElement(1,i).getStyleClass().add("winner-Button");
                this.getElement(2,i).getStyleClass().add("winner-Button");
                return true;
            }
            else if (xoBoard[0][0] == computer && xoBoard[1][1] == computer && xoBoard[2][2] == computer){
                this.getElement(0,0).getStyleClass().removeAll("non-Button");
                this.getElement(1,1).getStyleClass().removeAll("non-Button");
                this.getElement(2,2).getStyleClass().removeAll("non-Button");
                this.getElement(0,0).getStyleClass().add("winner-Button");
                this.getElement(1,1).getStyleClass().add("winner-Button");
                this.getElement(2,2).getStyleClass().add("winner-Button");
                return true;
            }
            else if (xoBoard[0][2] == computer && xoBoard[1][1] == computer && xoBoard[2][0] == computer){
                this.getElement(0,2).getStyleClass().removeAll("non-Button");
                this.getElement(1,1).getStyleClass().removeAll("non-Button");
                this.getElement(2,0).getStyleClass().removeAll("non-Button");
                this.getElement(0,2).getStyleClass().add("winner-Button");
                this.getElement(1,1).getStyleClass().add("winner-Button");
                this.getElement(2,0).getStyleClass().add("winner-Button");
                return true;
            }
        }
        return false;
    }
     void restart(){
        computerScore =0;
        draw =0;
    }
}
