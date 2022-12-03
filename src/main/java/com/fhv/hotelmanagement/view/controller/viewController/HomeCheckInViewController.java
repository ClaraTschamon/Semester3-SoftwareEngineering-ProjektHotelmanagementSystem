package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.view.controller.useCaseController.UnnoetigerUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class HomeCheckInViewController {
    private UnnoetigerUseCaseController useCaseController;


    @FXML
    private void onWalkInClicked(ActionEvent e) throws IOException {
        WalkInViewController walkInViewControllerController = new WalkInViewController();
    }

    protected UnnoetigerUseCaseController getUseCaseController() {
        return useCaseController;
    }

    public HomeCheckInViewController() {
        try {
            useCaseController = new UnnoetigerUseCaseController();
            loader();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loader() throws IOException {
        FXMLLoader homecheckinloader = new FXMLLoader(getClass().getResource("home-Check-In"));
        HomeCheckInViewController homeCheckInViewController = homecheckinloader.getController();
        HomeCheckInViewController.setController(this);
    }

    private static void setController(HomeCheckInViewController homeCheckInViewController) {
    }


}

