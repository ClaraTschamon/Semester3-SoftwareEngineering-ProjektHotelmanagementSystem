package com.fhv.hotelmanagement.domain.exceptions;

public class ReservationIsInvalidException extends Exception{

    private static final long serialVersionUID = 1L;

    public ReservationIsInvalidException() {
        super("This reservation is invalid.");
    }

    public ReservationIsInvalidException(String info) {
        super("This reservation is invalid: " + info);
    }
}
