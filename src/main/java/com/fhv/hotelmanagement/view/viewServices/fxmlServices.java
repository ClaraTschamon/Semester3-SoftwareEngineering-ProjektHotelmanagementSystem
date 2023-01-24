//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.viewServices;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class fxmlServices {

    private void setTextColor(TextField textField, String color) {
        textField.setStyle("-fx-text-inner-color: " + color);
    }

    private void setRequieredField(TextField textField) {
        textField.setPromptText("Pflichtfeld");
        textField.setStyle("-fx-prompt-text-fill: red");
    }

    private void setEventHandler(TextField textField){
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setTextColor(textField, "black");
            }
        });
    }
}
