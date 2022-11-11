package com.fhv.hotelmanagement.domain.domainModel;

public class CustomerDoesNotExistException extends Exception{

    private static final long serialVersionUID = 1L;

    public CustomerDoesNotExistException(){
        super("This customer does not exist.");
    }

}
