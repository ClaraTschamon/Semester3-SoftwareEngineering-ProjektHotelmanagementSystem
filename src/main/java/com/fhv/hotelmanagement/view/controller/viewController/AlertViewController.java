package com.fhv.hotelmanagement.view.controller.viewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertViewController {
    @FXML
    AnchorPane iconAnchorPane;
    @FXML
    AnchorPane labelAnchorPane;


    public void setText(String text) {
        Label label = new Label();
        label.setText(text);
        labelAnchorPane.getChildren().setAll(label);
    }
}
