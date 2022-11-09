package com.fhv.hotelmanagement.controller.useCaseController;

import com.fhv.hotelmanagement.DTOs.*;
import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.controller.viewController.WalkIn1ViewController;
import com.fhv.hotelmanagement.controller.viewController.WalkIn2ViewController;
import com.fhv.hotelmanagement.controller.viewController.WalkIn3ViewController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class WalkInUseCaseController {
    BookingDTO booking;
    CustomerDTO customer;

    public WalkInUseCaseController() throws IOException {
        booking = new BookingDTO();
        customer = new CustomerDTO();
        loadWalkIn1();
    }

    public void loadWalkIn1() throws IOException {
        FXMLLoader walkIn1Loader = MainApplication.getMainController().loadIntoContentArea("walk-in-1");
        WalkIn1ViewController walkIn1ViewController = walkIn1Loader.getController();
        walkIn1ViewController.setUseCaseController(this);
        walkIn1ViewController.fillData();
    }

    public void loadWalkIn2() throws IOException {
        FXMLLoader walkIn2Loader = MainApplication.getMainController().loadIntoContentArea("walk-in-2");
        WalkIn2ViewController walkIn2ViewController = walkIn2Loader.getController();
        walkIn2ViewController.setUseCaseController(this);
    }

    public void loadWalkIn3() throws IOException {
        FXMLLoader walkIn3Loader = MainApplication.getMainController().loadIntoContentArea("walk-in-3");
        WalkIn3ViewController walkIn3ViewController = walkIn3Loader.getController();
        walkIn3ViewController.setUseCaseController(this);
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void cancel() throws IOException {
        booking = null;
        customer = null;
        MainApplication.getMainController().loadIntoContentArea("home");
    }

    public void save() throws IOException {
        if (booking != null && customer != null) {
            // TODO
        }
        MainApplication.getMainController().loadIntoContentArea("home");
    }
}
