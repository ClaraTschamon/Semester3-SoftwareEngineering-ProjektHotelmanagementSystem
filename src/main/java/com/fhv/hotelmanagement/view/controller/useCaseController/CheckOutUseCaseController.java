package com.fhv.hotelmanagement.view.controller.useCaseController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;

import java.time.LocalDateTime;

public class CheckOutUseCaseController {
    private BookingDTO booking;

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public void checkOut() {
        if (booking != null) {
            booking.setCheckOutDatetime(LocalDateTime.now());
            try {
                DomainController.saveBooking(booking);
            } catch (BookingIsInvalidException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
