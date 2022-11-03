package com.fhv.hotelmanagement.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.CustomerEntity;

import java.time.LocalDate;

public class Customer {

    private CustomerEntity entity;
    private int number;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private String phoneNumber;
    private String email;
    private Address address;
    private String creditCardNumber;
    private Boolean saved;

    //bookings Liste??

    public Customer(CustomerEntity entity) {
        this.entity = entity;
        this.number = entity.getNumber();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.dateOfBirth = entity.getDateOfBirth();
        this.nationality = entity.getNationality();
        this.phoneNumber = entity.getPhoneNumber();
        this.email = entity.getEmail();
        this.address = new Address(entity.getStreet(), entity.getHouseNumber(), entity.getPostalCode(), entity.getCity(), entity.getCountry());
        this.creditCardNumber = entity.getCreditCardNumber();
        this.saved = entity.getSaved();
    }

    public CustomerEntity getEntity(){
        return entity;
    }
}
