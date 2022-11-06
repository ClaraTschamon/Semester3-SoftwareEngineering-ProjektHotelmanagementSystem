package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;


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