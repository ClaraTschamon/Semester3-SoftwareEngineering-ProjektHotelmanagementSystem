package com.fhv.hotelmanagement.domain.exceptions;

public class BookingIsInvalidException extends Exception {
    private static final long serialVersionUID = 1L;

    public BookingIsInvalidException(){
        super("This booking is invalid.");
    }
}
