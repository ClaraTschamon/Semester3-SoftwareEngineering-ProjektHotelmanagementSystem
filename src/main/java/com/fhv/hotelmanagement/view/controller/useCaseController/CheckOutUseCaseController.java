//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.useCaseController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.domain.exceptions.BookingIsInvalidException;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.DTOs.InvoiceDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CheckOutUseCaseController {
    private BookingDTO booking;

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void save() {
        if (booking != null) {
            LocalDate today = LocalDate.now();
            booking.setCheckOutDatetime(LocalDateTime.now());

            for (BookedRoomDTO bookedRoomDTO : booking.getBookedRooms()) {
                if (bookedRoomDTO.getFromDate().isAfter(today)) {
                    bookedRoomDTO.setFromDate(null);
                    bookedRoomDTO.setToDate(null);
                } else if (bookedRoomDTO.getToDate().isAfter(today)) {
                    bookedRoomDTO.setToDate(today);
                }
            }

            try {
                DomainController.saveBooking(booking);
                DomainController.saveInvoice(new InvoiceDTO(booking));
            } catch (BookingIsInvalidException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
