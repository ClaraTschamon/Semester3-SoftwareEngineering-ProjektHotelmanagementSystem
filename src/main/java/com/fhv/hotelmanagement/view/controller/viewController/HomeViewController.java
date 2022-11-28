package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.domainModel.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class HomeViewController{

    @FXML
    private void onWalkInClicked(ActionEvent e) throws IOException {
        WalkInViewController walkInViewControllerController = new WalkInViewController();
    }

    @FXML
    private void onCheckOutClicked(ActionEvent e) throws IOException{
        CheckOutViewController checkOutViewController = new CheckOutViewController();
        //FXMLLoader walkIn1Loader = MainApplication.getMainController().loadIntoContentArea("check-out-1");
        //TODO: warum walkIn1Loader??
    }


}