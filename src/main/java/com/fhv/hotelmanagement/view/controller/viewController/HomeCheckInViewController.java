package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.view.controller.useCaseController.CheckOutUseCaseController;
import com.fhv.hotelmanagement.view.controller.useCaseController.UnnoetigerUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class HomeCheckInViewController {
    private HomeViewController viewController;

    @FXML
    private void onWalkInClicked(ActionEvent e) throws IOException {
        WalkInViewController walkInViewControllerController = new WalkInViewController();
    }

    /*public void setController(HomeViewController viewController) {
        this.viewController = viewController;
    }*/

}

