package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domainModel.Customer;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.CustomerEntity;

import java.util.Optional;

public class CustomerDataMapper{

    private CustomerDataMapper(){}
    private static CustomerDataMapper _instance = new CustomerDataMapper();
    public static CustomerDataMapper instance(){
        return _instance;
    }

    //read
    public Optional<Customer> get(final int number){
        CustomerEntity entity = PersistenceFacade.instance().entityManager.find(CustomerEntity.class, number);
        if(entity != null){
            Customer customer = new Customer(entity);
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    //create
    public void insert(Customer customer){
        var entityManager = PersistenceFacade.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(customer.getEntity());
        entityManager.getTransaction().commit();
    }

    //update
    public void store(Customer customer){
        PersistenceFacade.instance().entityManager.merge(customer.getEntity());
    }
}
