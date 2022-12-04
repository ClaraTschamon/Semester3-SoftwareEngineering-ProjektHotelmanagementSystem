//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class HomeViewController{


    @FXML
    private void onHomeCheckInClicked(ActionEvent e) throws IOException {
        FXMLLoader homeCheckInLoader = new FXMLLoader(getClass().getResource("fxml/home-Check-In.fxml"));
        HomeCheckInViewController homeCheckInViewController = homeCheckInLoader.getController();
        //homeCheckInViewController.setController(this);
    }


    @FXML
    private void onCheckOutClicked(ActionEvent e) throws IOException{
        CheckOutViewController checkOutViewController = new CheckOutViewController();
    }
}