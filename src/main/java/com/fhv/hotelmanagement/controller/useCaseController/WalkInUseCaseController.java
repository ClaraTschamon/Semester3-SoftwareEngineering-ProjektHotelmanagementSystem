package com.fhv.hotelmanagement.controller.useCaseController;

import com.fhv.hotelmanagement.domainModel.Booking;
import com.fhv.hotelmanagement.domainModel.Customer;

public class WalkInUseCaseController {
    Booking booking;
    Customer customer;

    public WalkInUseCaseController() {
        booking = new Booking();
        customer = new Customer();
    }

    public Booking getBooking() {
        return booking;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void cancel() {
        booking = null;
        customer = null;
    }

    public void save() {
        if (booking != null && customer != null) {
            // TODO
        }
    }
}
