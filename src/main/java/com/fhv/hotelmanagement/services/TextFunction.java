package com.fhv.hotelmanagement.services;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TextFunction {

    public static void setTextFieldColor(TextField textField, String color) {
        textField.setStyle("-fx-text-inner-color: " + color);
    }

    public static void setRequieredTextField(TextField textField) {
        textField.setPromptText("Pflichtfeld");
        textField.setStyle("-fx-prompt-text-fill: red");
    }

    public static void setEventHandler(TextField textField) {
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) { setTextFieldColor(textField, "black");
            }
        });
    }

}
