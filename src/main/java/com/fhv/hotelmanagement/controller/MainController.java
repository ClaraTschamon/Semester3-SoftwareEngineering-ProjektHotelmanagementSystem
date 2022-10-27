package com.fhv.hotelmanagement.controller;

import com.fhv.hotelmanagement.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane bp;

    @FXML
    private void home(MouseEvent event) {
        loadPage("home");
    }

    @FXML
    private void bookings(MouseEvent event) {
        loadPage("bookings");
    }

    private void loadPage(String page) {
        Parent selectedPage = null;
        try {
            System.out.println("---"+ page + ": " + FXMLLoader.load(MainApplication.class.getResource("fxml/" + page + ".fxml")));
            selectedPage = FXMLLoader.load(MainApplication.class.getResource("fxml/" + page + ".fxml"));
            bp.setCenter(selectedPage);
        } catch (IOException e) {
            System.out.println("Error loading page: " + e);
        }

    }
}