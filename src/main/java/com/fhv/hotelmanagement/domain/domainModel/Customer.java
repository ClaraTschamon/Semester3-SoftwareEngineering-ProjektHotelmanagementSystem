package com.fhv.hotelmanagement.domain.domainModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer {

    private static int id;
    private int number;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private String phoneNumber;
    private String email;
    private Address address;
    private boolean saved;
    private ArrayList<Booking> bookings;


    public Customer(Integer number, String firstName, String lastName, LocalDate dateOfBirth, String nationality,
                    String phoneNumber, String email, String street, String houseNumber, String postalCode, String city,
                    String country, boolean saved, ArrayList<Booking> bookings) {
        if (number == null) {
            number = getId();
        }
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = new Address(street, houseNumber, postalCode, city, country);
        this.saved = saved;
        this.bookings = bookings;
        id++;
    }

    public int getNumber() {
        return this.number;
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

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    //Id braucht nur getter weil sie statisch ist
    private static int getId() {
        return id;
    }
}
