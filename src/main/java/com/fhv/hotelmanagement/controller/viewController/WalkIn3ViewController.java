package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.controller.useCaseController.WalkInUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URISyntaxException;

public class WalkIn3ViewController {
    WalkInUseCaseController useCaseController;

    public void setUseCaseController(WalkInUseCaseController useCaseController) {
        this.useCaseController = useCaseController;
    }

    @FXML
    public void onBackButtonClickedPayment(ActionEvent e) throws IOException {
        useCaseController.loadWalkIn2();
    }

    @FXML
    public void onSaveButtonClicked(ActionEvent e) throws IOException {
        // TODO fill all attributes

        useCaseController.save();
    }

    @FXML
    public void onCancelButtonClickedPayment(ActionEvent e) throws IOException {
        useCaseController.cancel();
    }
}
