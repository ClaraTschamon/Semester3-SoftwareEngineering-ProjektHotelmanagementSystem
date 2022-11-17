package com.fhv.hotelmanagement.view.DTOs;

public class AddressDTO {
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String country;
    private boolean billingAddressEqualsCustomerAddress = true;

    public AddressDTO() {}

    public AddressDTO(String street, String houseNumber, String postalCode, String city, String country) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isBillingAddressEqualsCustomerAddress() {
        return billingAddressEqualsCustomerAddress;
    }

    public void setBillingAddressEqualsCustomerAddress(boolean billingAddressEqualsCustomerAddress) {
        this.billingAddressEqualsCustomerAddress = billingAddressEqualsCustomerAddress;
    }
}
