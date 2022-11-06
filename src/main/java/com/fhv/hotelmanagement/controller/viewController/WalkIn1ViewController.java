package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class WalkIn1ViewController {

    @FXML
    private Text chooseRoom;

    @FXML
    private Text departureDate;

    @FXML
    private Text room;

    @FXML
    private Text roomPrice;

    //package fehlt

    @FXML
    private void onCancelButtonClicked(ActionEvent e){
        try{
            MainApplication.getMainController().loadIntoContentArea("home");
        }catch(IOException | URISyntaxException exc){
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e){
        try{
            MainApplication.getMainController().loadIntoContentArea("walk-in-2");
        }catch(IOException | URISyntaxException exc){
            System.out.println(exc.getMessage());
        }
    }

}
