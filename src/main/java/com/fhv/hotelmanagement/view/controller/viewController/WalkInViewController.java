//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.domain.exceptions.CustomerIsInvalidException;
import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;
import com.fhv.hotelmanagement.view.viewServices.WarningType;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import com.fhv.hotelmanagement.view.controller.useCaseController.*;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;

public class WalkInViewController {
    private WalkInUseCaseController useCaseController;
    private boolean searching;

    public WalkInViewController() throws IOException {
        useCaseController = new WalkInUseCaseController();
        searching = false;
        loadWalkIn1();
    }

    protected WalkInUseCaseController getUseCaseController() {
        return useCaseController;
    }

    public void loadWalkIn1() throws IOException {
        searching = false;
        FXMLLoader walkIn1Loader = MainApplication.getMainController().loadIntoContentArea("walk-in-1");
        com.fhv.hotelmanagement.view.controller.viewController.WalkIn1ViewController walkIn1ViewController = walkIn1Loader.getController();
        walkIn1ViewController.setController(this);
        walkIn1ViewController.fillData();
    }

    public void loadWalkIn2() throws IOException {
        searching = false;
        FXMLLoader walkIn2Loader = MainApplication.getMainController().loadIntoContentArea("walk-in-2");
        WalkIn2ViewController walkIn2ViewController = walkIn2Loader.getController();
        walkIn2ViewController.setController(this);
        walkIn2ViewController.fillData();
    }

    public void loadWalkIn3() throws IOException {
        searching = false;
        FXMLLoader walkIn3Loader = MainApplication.getMainController().loadIntoContentArea("walk-in-3");
        WalkIn3ViewController walkIn3ViewController = walkIn3Loader.getController();
        walkIn3ViewController.setController(this);
        walkIn3ViewController.fillData();
    }

    public void cancel() throws IOException {
        useCaseController.cancel();
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    public void save() {
        try {
            useCaseController.save();
            MainApplication.getMainController().loadIntoContentArea("booking-overview");
            MainApplication.getMainController().alert("The booking was saved successfully.",
                    WarningType.CONFIRMATION);
        } catch (BookingIsInvalidException e) {
            System.out.println(e.getMessage());
            MainApplication.getMainController().alert("Invalid booking: The booking could not be saved.",
                    WarningType.WARNING);
        } catch (CustomerIsInvalidException e) {
            System.out.println(e.getMessage());
            MainApplication.getMainController().alert("Invalid customer: The customer could not be saved.",
                    WarningType.WARNING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            MainApplication.getMainController().alert("The screen could not be changed.",
                    WarningType.WARNING);
        }
    }
}
