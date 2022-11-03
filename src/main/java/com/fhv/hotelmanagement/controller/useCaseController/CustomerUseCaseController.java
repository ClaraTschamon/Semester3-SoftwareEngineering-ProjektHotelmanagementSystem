package com.fhv.hotelmanagement.controller.useCaseController;

import com.fhv.hotelmanagement.domainModel.Customer;
import com.fhv.hotelmanagement.domainModel.CustomerDoesNotExistException;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;

import java.util.Optional;

public class CustomerUseCaseController {

    public Customer getCustomer(int number) throws CustomerDoesNotExistException {

        Optional<Customer> customer = PersistenceFacade.instance().getCustomer(number);
        if(customer.isPresent()){
            return customer.get();
        }
        throw new CustomerDoesNotExistException();
    }
}
