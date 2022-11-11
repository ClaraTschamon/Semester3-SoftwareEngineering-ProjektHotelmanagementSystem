package com.fhv.hotelmanagement.domain.domainModel;

public class Address {
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String country;

    public Address(String street, String houseNumber, String postalCode, String city, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }
}
