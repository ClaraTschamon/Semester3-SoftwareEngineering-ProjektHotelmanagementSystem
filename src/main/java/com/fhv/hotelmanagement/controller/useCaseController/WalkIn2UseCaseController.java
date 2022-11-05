package com.fhv.hotelmanagement.controller.useCaseController;

import com.fhv.hotelmanagement.domainModel.Customer;
import com.fhv.hotelmanagement.domainModel.CustomerDoesNotExistException;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.CustomerEntity;

import java.util.Optional;

public class WalkIn2UseCaseController {
    public Customer getCustomer(int number) throws CustomerDoesNotExistException {

        Optional<Customer> customer = PersistenceFacade.instance().getCustomer(number);
        if(customer.isPresent()){
            return customer.get();
        }
        throw new CustomerDoesNotExistException();
    }

    public void storeCustomer(CustomerEntity customerEntity){
        PersistenceFacade.instance().entityManager.persist(customerEntity);
    }
}
