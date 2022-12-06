package com.fhv.hotelmanagement;

import com.fhv.hotelmanagement.view.controller.viewController.BookingOverviewViewController;
import com.fhv.hotelmanagement.view.controller.viewController.HomeViewController;
import com.fhv.hotelmanagement.view.viewServices.WarningType;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
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

    public void alert(String message, WarningType warningType) {
        String color = "#21273d";
        String iconName = "";
        switch (warningType) {
            case ERROR:
                color = "#962c43";
                iconName = "error";
                break;
            case WARNING:
                color = "#D3AB1B";
                iconName = "warning";
                break;
            case INFORMATION:
                color = "#21273d";
                iconName = "information";
                break;
            case CONFIRMATION:
                color = "#30A41E";
                iconName = "confirmation";
                break;
        }

        AnchorPane alertPane = new AnchorPane();
        alertPane.setStyle("-fx-border-color: " + color + ";" +
                "-fx-border-width: 2px;" +
                "-fx-background-color: white;" +
                "-fx-min-height: 80; -fx-max-height: 120;" +
                "-fx-min-width: 400; -fx-max-width: 400;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;");

        Label label = new Label();
        label.setText(message);
        label.setStyle("-fx-min-height: 60; -fx-max-height: 100;" +
                "-fx-min-width: 300; -fx-max-width: 300;" +
                "-fx-font-size: 14;");
        label.setLayoutX(80);
        label.setLayoutY(4);
        alertPane.getChildren().add(label);

        Image icon;
        try {
            icon = new Image(MainApplication.class.getResource("fxml/Bilder/icons/"+iconName+".png").openStream());
            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);
            imageView.setLayoutX(10);
            imageView.setLayoutY(10);
            alertPane.getChildren().add(imageView);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        alertPane.setLayoutX(contentArea.getWidth() / 2 - 200);
        alertPane.setLayoutY(4);

        contentArea.getChildren().add(alertPane);
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(e -> contentArea.getChildren().remove(alertPane));
        delay.play();
    }

    @FXML
    public void onBookingsClicked(ActionEvent actionEvent) throws IOException {
        try{
            BookingOverviewViewController bookingOverviewViewController = new BookingOverviewViewController();
            loadIntoContentArea("booking-overview");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void onHomeClicked(ActionEvent actionEvent) {
        try{
            HomeViewController homeViewController = new HomeViewController();
            //homeViewController.createBarChart();
            loadIntoContentArea("home");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}