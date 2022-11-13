package com.fhv.hotelmanagement.view.controller.useCaseController;

import com.fhv.hotelmanagement.domain.domainController.DomainController;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WalkInUseCaseController {
    BookingDTO booking;
    CustomerDTO customer;
    BoardDTO boardDTO;
    RoomDTO roomDTO;

    public WalkInUseCaseController() throws IOException {
        booking = new BookingDTO();
        customer = new CustomerDTO();
        booking.setCustomer(customer);
        booking.setArrivalDate(LocalDate.now());
        boardDTO = new BoardDTO();
        roomDTO = new RoomDTO();

        // TODO take into ui
        customer.setSaved(true);
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public BoardDTO getPackage(){return boardDTO;}

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void cancel() throws IOException {
        booking = null;
        customer = null;
        boardDTO=null;
        roomDTO=null;
    }

    public void save() throws IOException {
        if (booking != null && customer != null) {
            DomainController.saveCustomer(customer);
            booking.setCheckInDatetime(LocalDateTime.now());
            DomainController.saveBooking(booking);
        }
    }
}
