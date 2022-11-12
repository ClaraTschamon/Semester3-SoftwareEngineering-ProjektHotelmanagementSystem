package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @Column(name = "booking_number")
    private int number;

    @ManyToOne
    @JoinColumn(name = "customer_number")
    private CustomerEntity customer;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "check_in_datetime")
    private LocalDateTime checkInDatetime;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "check_out_datetime")
    private LocalDateTime checkOutDatetime;

    @Column(name = "billing_street")
    private String billingStreet;

    @Column(name = "billing_house_number")
    private String billingHouseNumber;

    @Column(name = "billing_postal_code")
    private String BillingPostalCode;

    @Column(name = "billing_city")
    private String BillingCity;

    @Column(name = "billing_country")
    private String BillingCountry;

    @Column(name="comment")
    private String comment;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="credit_card_number")
    private String creditCardNumber;

    @Column(name="expiration_date")
    private LocalDate expirationDate;

    @Column(name="authorisation_number")
    private String authorisationNumber;

    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<BookedRoomCategoryEntity> bookedRoomCategories;

    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<BookedRoomEntity> bookedRooms;

    public BookingEntity(){};

    public BookingEntity(int number, CustomerEntity customer, LocalDate arrivalDate,
                         LocalDateTime checkInDatetime, LocalDate departureDate,
                         LocalDateTime checkOutDatetime, String billingStreet,
                         String billingHouseNumber, String billingPostalCode,
                         String billingCity, String billingCountry,
                         Set<BookedRoomCategoryEntity> bookedRoomCategories,
                         Set<BookedRoomEntity> bookedRooms) {
        this.number = number;
        this.customer = customer;
        this.arrivalDate = arrivalDate;
        this.checkInDatetime = checkInDatetime;
        this.departureDate = departureDate;
        this.checkOutDatetime = checkOutDatetime;
        this.billingStreet = billingStreet;
        this.billingHouseNumber = billingHouseNumber;
        BillingPostalCode = billingPostalCode;
        BillingCity = billingCity;
        BillingCountry = billingCountry;
        this.bookedRoomCategories = bookedRoomCategories;
        this.bookedRooms = bookedRooms;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }


    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }


    public LocalDateTime getCheckInDatetime() {
        return checkInDatetime;
    }

    public void setCheckInDatetime(LocalDateTime checkInDatetime) {
        this.checkInDatetime = checkInDatetime;
    }


    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }


    public LocalDateTime getCheckOutDatetime() {
        return checkOutDatetime;
    }

    public void setCheckOutDatetime(LocalDateTime checkOutDatetime) {
        this.checkOutDatetime = checkOutDatetime;
    }


    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }


    public String getBillingHouseNumber() {
        return billingHouseNumber;
    }

    public void setBillingHouseNumber(String billingHouseNumber) {
        this.billingHouseNumber = billingHouseNumber;
    }


    public String getBillingPostalCode() {
        return BillingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        BillingPostalCode = billingPostalCode;
    }


    public String getBillingCity() {
        return BillingCity;
    }

    public void setBillingCity(String billingCity) {
        BillingCity = billingCity;
    }


    public String getBillingCountry() {
        return BillingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        BillingCountry = billingCountry;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAuthorisationNumber() {
        return authorisationNumber;
    }

    public void setAuthorisationNumber(String authorisationNumber) {
        this.authorisationNumber = authorisationNumber;
    }

    public Set<BookedRoomCategoryEntity> getBookedRoomCategories() {
        return bookedRoomCategories;
    }

    public void setBookedRoomCategories(Set<BookedRoomCategoryEntity> bookedRoomCategories) {
        this.bookedRoomCategories = bookedRoomCategories;
    }

    public Set<BookedRoomEntity> getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(Set<BookedRoomEntity> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "number=" + number +
                ", customer=" + customer +
                ", arrivalDate=" + arrivalDate +
                ", checkInDatetime=" + checkInDatetime +
                ", departureDate=" + departureDate +
                ", checkOutDatetime=" + checkOutDatetime +
                ", billingStreet='" + billingStreet + '\'' +
                ", billingHouseNumber='" + billingHouseNumber + '\'' +
                ", BillingPostalCode='" + BillingPostalCode + '\'' +
                ", BillingCity='" + BillingCity + '\'' +
                ", BillingCountry='" + BillingCountry + '\'' +
                ", bookedRoomCategories=" + bookedRoomCategories +
                ", bookedRooms=" + bookedRooms +
                '}';
    }
}