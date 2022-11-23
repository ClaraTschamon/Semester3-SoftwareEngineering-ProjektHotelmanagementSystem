package com.fhv.hotelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane contentArea;
    private FXMLLoader currentFXMLLoader;


    public FXMLLoader loadIntoContentArea(String resourceToLoad) throws IOException {
        currentFXMLLoader = new FXMLLoader(MainApplication.class.getResource("fxml/"+ resourceToLoad + ".fxml"));
        Parent fxml = currentFXMLLoader.load();
        contentArea.getChildren().clear();
        contentArea.getChildren().setAll(fxml);
        return currentFXMLLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadIntoContentArea("home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FXMLLoader getCurrentFXMLLoader() {
        return currentFXMLLoader;
    }

    public void debugAlert() {
        System.out.println("alert");
        AnchorPane alertPane = new AnchorPane();
        Label label = new Label();
        label.setText("alert");
        alertPane.getChildren().setAll(label);
        label.setStyle("-fx-background-color: #00d7ff; -fx-min-height: 30; -fx-min-width: 30");
        alertPane.setLayoutX(400);
        alertPane.setLayoutY(400);
        contentArea.getChildren().add(alertPane);
    }
}