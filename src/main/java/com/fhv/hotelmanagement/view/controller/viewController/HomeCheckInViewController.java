package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.view.controller.useCaseController.BookingOverviewUseCaseController;
import com.fhv.hotelmanagement.view.controller.useCaseController.UnnoetigerUseCaseController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeCheckInViewController {
    private UnnoetigerUseCaseController useCaseController;

    @FXML
    private void onWalkInClicked(ActionEvent e) throws IOException {
        WalkInViewController walkInViewControllerController = new WalkInViewController();
    }

    private void addRelease(Event event) throws IOException {
        Popup popup = new Popup();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/home-Check-In.fxml"));
        Parent parent = (Parent)loader.load();
        HomeCheckInViewController controller = loader.getController();
        popup.getContent().add(parent);
    }


}

