package com.fhv.hotelmanagement.view.viewController.useCaseController;

import com.fhv.hotelmanagement.view.DTOs.*;

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
