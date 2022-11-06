package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "customer")
public class CustomerEntity {
    private int number;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private String phoneNumber;
    private String email;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String country;
    private String creditCardNumber;
    private Boolean saved;
    private Set<BookingEntity> bookings;

    public CustomerEntity(){};

    public CustomerEntity(int number, String firstName, String lastName,
                          LocalDate dateOfBirth, String nationality, String phoneNumber,
                          String email, String street, String houseNumber, String postalCode,
                          String city, String country, String creditCardNumber, Boolean saved,
                          Set<BookingEntity> bookings) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.creditCardNumber = creditCardNumber;
        this.saved = saved; //für was steht saved??
        this.bookings = bookings;
    }

    @Id
    @Column(name = "customer_number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Column(name = "customer_first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "customer_last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "customer_date_of_birth")
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "customer_nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Column(name = "customer_phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "customer_email_address")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "customer_street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "customer_house_number")
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Column(name = "customer_postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "customer_city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "customer_country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "customer_credit_card_number")
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Column(name = "saved")
    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public Set<BookingEntity> getBookings() {
        return bookings;
    }


    public void setBookings(Set<BookingEntity> bookings) {
        this.bookings = bookings;
    }
}
