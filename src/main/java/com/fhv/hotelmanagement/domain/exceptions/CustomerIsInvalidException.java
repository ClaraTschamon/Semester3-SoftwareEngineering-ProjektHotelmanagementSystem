package com.fhv.hotelmanagement.domain.exceptions;

public class CustomerIsInvalidException extends Exception {
    private static final long serialVersionUID = 1L;

    public CustomerIsInvalidException(){
        super("This customer is invalid.");
    }
}
