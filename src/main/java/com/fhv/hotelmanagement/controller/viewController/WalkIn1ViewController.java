package com.fhv.hotelmanagement.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.controller.useCaseController.WalkInUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.ResourceBundle;

public class WalkIn1ViewController implements Initializable {

    @FXML
    private Text chooseRoom;

    @FXML
    private DatePicker departureDate;

    @FXML
    private Text room;

    @FXML
    private Text roomPrice;
    private WalkInUseCaseController useCaseController;

    //package fehlt

    @FXML
    private void onCancelButtonClicked(ActionEvent e) {
        try {
            useCaseController.cancel();
            MainApplication.getMainController().loadIntoContentArea("home");
        } catch (IOException | URISyntaxException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void onNextButtonClicked(ActionEvent e) {
        try {
            useCaseController.getBooking().setDepartureDate(departureDate.getValue());
            // TODO fill all attributes
            MainApplication.getMainController().loadIntoContentArea("walk-in-2");
            WalkIn2ViewController walkIn2ViewController = MainApplication.getMainController().getCurrentFXMLLoader().getController();
            walkIn2ViewController.setUseCaseController(useCaseController);
        } catch (IOException | URISyntaxException exc) {
            System.out.println(exc.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        useCaseController = new WalkInUseCaseController();
    }
}
