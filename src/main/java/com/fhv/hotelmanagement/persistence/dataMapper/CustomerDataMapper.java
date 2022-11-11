package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Address;
import com.fhv.hotelmanagement.domain.domainModel.Customer;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookingEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.CustomerEntity;

import java.util.HashSet;
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
            Customer customer = createCustomer(entity);
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    //create
    public void insert(Customer customer){
        var entityManager = PersistenceFacade.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(createCustomerEntity(customer));
        entityManager.getTransaction().commit();
    }

    //update
    public void store(Customer customer){
        PersistenceFacade.instance().entityManager.merge(createCustomerEntity(customer));
    }

    protected CustomerEntity createCustomerEntity(Customer customer) {
        Address address = customer.getAddress();
        return new CustomerEntity(customer.getNumber(), customer.getFirstName(), customer.getLastName(), customer.getDateOfBirth(),
                customer.getNationality(), customer.getPhoneNumber(), customer.getEmail(), address.getStreet(), address.getHouseNumber(),
                address.getPostalCode(), address.getCity(), address.getCountry(), customer.getSaved(), new HashSet<BookingEntity>());
    }

    protected Customer createCustomer(CustomerEntity customerEntity) {
        return new Customer(customerEntity.getNumber(), customerEntity.getFirstName(), customerEntity.getLastName(),
                customerEntity.getDateOfBirth(), customerEntity.getNationality(), customerEntity.getPhoneNumber(),
                customerEntity.getEmail(), customerEntity.getStreet(), customerEntity.getHouseNumber(),
                customerEntity.getPostalCode(), customerEntity.getCity(), customerEntity.getCountry(), customerEntity.getSaved());
    }
}
