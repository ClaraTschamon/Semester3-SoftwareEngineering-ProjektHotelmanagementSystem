package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URISyntaxException;

public class WalkIn3ViewController {

    @FXML
    public void onBackButtonClickedPayment(ActionEvent e) throws IOException, URISyntaxException {
        MainApplication.getMainController().loadIntoContentArea("walk-in-2");
    }

    @FXML
    public void onCancelButtonClickedPayment(ActionEvent e) throws IOException, URISyntaxException {
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent e) throws IOException, URISyntaxException {
        MainApplication.getMainController().loadIntoContentArea("home");
    }
}
