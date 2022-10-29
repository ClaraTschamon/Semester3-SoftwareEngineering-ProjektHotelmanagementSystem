package com.fhv.hotelmanagement.domainModel;

import java.time.LocalDate;

public class Customer {
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

    public Customer(int number, String firstName, String lastName, LocalDate dateOfBirth, String nationality,
                    String phoneNumber, String email, Address address, String creditCardNumber, Boolean saved) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.saved = saved;
    }
}
