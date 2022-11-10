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
    }

    public void save() throws IOException {
        if (booking != null && customer != null) {
            // TODO
        }
    }
}
