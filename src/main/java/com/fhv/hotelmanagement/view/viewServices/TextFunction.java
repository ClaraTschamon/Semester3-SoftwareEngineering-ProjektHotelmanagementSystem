//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.viewServices;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TextFunction {

    public static void setTextFieldColor(TextField textField, String color) {
        textField.setStyle("-fx-text-inner-color: " + color);
    }

    public static void setRequieredTextField(TextField textField) {
        textField.setPromptText("Required Field");
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
