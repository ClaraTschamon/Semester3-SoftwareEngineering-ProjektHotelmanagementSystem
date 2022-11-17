package com.fhv.hotelmanagement.view.DTOs;

import java.time.LocalDate;

public class CustomerDTO {

    private Long number;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private String phoneNumber;
    private String email;
    private AddressDTO address;
    private Boolean saved;

    public CustomerDTO() {
        this.address = new AddressDTO();
    }

    public CustomerDTO(Long number, String firstName, String lastName, LocalDate dateOfBirth,
                       String nationality, String phoneNumber, String email, AddressDTO address,
                       Boolean saved) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.saved = saved;
    }

    public CustomerDTO(String firstName, String lastName, LocalDate dateOfBirth,
                       String nationality, String phoneNumber, String email, AddressDTO address,
                       Boolean saved) {
        this(null, firstName, lastName, dateOfBirth, nationality, phoneNumber, email, address, saved);
    }

    public Long getNumber() {
        return number;
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }
}
