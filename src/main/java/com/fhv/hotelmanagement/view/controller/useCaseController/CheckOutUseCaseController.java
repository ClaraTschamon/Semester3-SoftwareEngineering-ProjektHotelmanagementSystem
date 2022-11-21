package com.fhv.hotelmanagement.view.controller.useCaseController;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.DTOs.InvoiceDTO;

import java.time.LocalDateTime;

public class CheckOutUseCaseController {
    private BookingDTO booking;

    public BookingDTO getBooking(Long number) {
        booking = MainApplication.getDomainManager().getBookingDTO(number);
        return booking;
    }

    public void save() {
        if (booking != null) {
            booking.setCheckOutDatetime(LocalDateTime.now());
            try {
                DomainController.saveBooking(booking);
                DomainController.saveInvoice(new InvoiceDTO(booking));
            } catch (BookingIsInvalidException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
