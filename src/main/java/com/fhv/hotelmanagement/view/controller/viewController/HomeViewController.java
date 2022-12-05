//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.MainController;
import com.fhv.hotelmanagement.view.viewServices.WarningType;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class HomeViewController {

    @FXML
    private AnchorPane contentArea;

    @FXML
    private void onCheckOutClicked(ActionEvent e) throws IOException {
        CheckOutViewController checkOutViewController = new CheckOutViewController();
    }

    @FXML
    private void onHomeCheckInClicked(ActionEvent e) throws FileNotFoundException {
        choice();
    }

    @FXML
    private void onWalkInClicked(ActionEvent e) throws IOException {
        WalkInViewController walkInViewControllerController = new WalkInViewController();
    }


    public void choice() throws FileNotFoundException {

        AnchorPane choice = new AnchorPane();
        choice.setLayoutX(100);
        choice.setLayoutY(100);
        choice.setStyle("-fx-background-color: #eeeeee; -fx-pref-height:450px; -fx-pref-width: 750px;");


        FileInputStream anhandReservierungInput = new FileInputStream();
        FileInputStream walkInInput = new FileInputStream("C:\\Users\\ninah\\Desktop\\FH Dornbirn\\Hotelmanagement\\src\\main\\resources\\com\\fhv\\hotelmanagement\\fxml\\Bilder\\Walk-In.png");
        
        Image anhandReservierungImage = new Image(anhandReservierungInput);
        Image walkInImage = new Image(walkInInput);

        ImageView anhandReservierungImageView = new ImageView(anhandReservierungImage);
        ImageView walkInImageView = new ImageView(walkInImage);

        Button walkInButton = new Button("Walk-In Guest", walkInImageView);
        Button anhandReservierungButton = new Button("Based on reservations", anhandReservierungImageView);

        walkInButton.setText("Walk-In Guest");
        anhandReservierungButton.setText("Based on reservations");

        walkInButton.setFont(new Font("System", 15));
        anhandReservierungButton.setFont(new Font("System", 15));

        walkInButton.setLayoutX(460);
        walkInButton.setLayoutY(130);

        anhandReservierungButton.setLayoutX(130);
        anhandReservierungButton.setLayoutY(130);

        //walkInButton.onActionProperty().addListener();

        anhandReservierungButton.setStyle("-fx-content-display: TOP;");
        walkInButton.setStyle("-fx-content-display: TOP;");


        choice.getChildren().add(anhandReservierungButton);
        choice.getChildren().add(walkInButton);
        contentArea.getChildren().add(choice);


        //FXML Datei Größe
        //insgesamt: 1100 Breite 650 Höhe

        //Home-Check-In: 700 Breite 450 Höhe


    }
}