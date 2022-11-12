package com.fhv.hotelmanagement.view.controller.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;


public class HomeViewController {

    @FXML
    private void onWalkInClicked(ActionEvent e) throws IOException {
        WalkInViewController walkInViewControllerController = new WalkInViewController();
    }
}