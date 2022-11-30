//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.exceptions;

public class CustomerDoesNotExistException extends Exception{

    private static final long serialVersionUID = 1L;

    public CustomerDoesNotExistException(){
        super("This customer does not exist.");
    }

}
