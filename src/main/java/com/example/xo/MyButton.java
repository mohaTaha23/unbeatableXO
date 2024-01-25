package com.example.xo;

import javafx.scene.control.Button;

public class MyButton extends Button {
    char player ;
    public MyButton(char a){
        this.player= a;
        this.setText(" ");
        this.setPrefSize(100,100);
        this.setOnAction(e->{
            if (this.getText().equals(" "))
                play();
        });
    }

    private void play() {
        this.setText(player+"");
    }
}
