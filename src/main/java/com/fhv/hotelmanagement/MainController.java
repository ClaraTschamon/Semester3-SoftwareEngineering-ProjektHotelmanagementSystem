package com.fhv.hotelmanagement;

import com.fhv.hotelmanagement.view.controller.viewController.AlertViewController;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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
        Stage popup = new Stage();
        popup.initStyle(StageStyle.UNDECORATED);
        popup.setAlwaysOnTop(true);
        popup.setX(MainApplication.stage.getX() + MainApplication.stage.getWidth()/2 - 200);
        popup.setY(MainApplication.stage.getY() + 5);
        try {
            FXMLLoader alertLoader = new FXMLLoader(MainApplication.class.getResource("fxml/alert.fxml"));

            popup.setScene(new Scene(alertLoader.load(), 40, 40));
            popup.show();
            AlertViewController alertController = alertLoader.getController();
            alertController.setText("this is an alerjlejrlekj alskd iewnidnsdlj ijweofinsoifn woiejfn kdfnwoiej idjflkwejl");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> popup.hide());
        delay.play();

//        System.out.println("call method");
//        AnchorPane alertPane = new AnchorPane();
//        Label label = new Label();
//        label.setText("alert");
//        alertPane.getChildren().setAll(label);
//        label.setStyle("-fx-background-color: #00d7ff; -fx-min-height: 30; -fx-min-width: 30");
//        alertPane.setLayoutX(400);
//        alertPane.setLayoutY(400);
//        try {
//            MainApplication.getMainController().getContentArea().getChildren().add(alertPane);
//            this.wait(2000);
//            MainApplication.getMainController().getContentArea().getChildren().remove(alertPane);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

    }
}