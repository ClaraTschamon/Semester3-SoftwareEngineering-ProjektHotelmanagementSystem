package com.fhv.hotelmanagement.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.CustomerEntity;

import java.time.LocalDate;

public class Customer {

    private static int id;

    private CustomerEntity entity;
    private int number;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private String phoneNumber;
    private String email;
    private Address address;
    private Boolean saved;
    private String city;
    private String street;
    private String postalCode;
    private String houseNumber;

    //bookings Liste??

    public Customer() {
        id++;
    }

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
        this.saved = entity.getSaved();
        this.city=entity.getCity();
        this.street=entity.getStreet();
        this.postalCode=entity.getPostalCode();
        this.houseNumber=entity.getHouseNumber();
    }

    public CustomerEntity getEntity(){
        return entity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    //Id braucht nur getter weil sie statisch ist
    public static int getId() {
        return id;
    }
}
