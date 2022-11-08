package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URISyntaxException;


public class HomeViewController {

    @FXML
    private void onWalkInClicked(ActionEvent e){
        try{
            MainApplication.getMainController().loadIntoContentArea("walk-in-1");
        }catch(IOException | URISyntaxException exc){
            System.out.println(exc.getMessage());
        }
    }
}