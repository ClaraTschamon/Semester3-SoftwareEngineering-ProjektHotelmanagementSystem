package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "booking")
public class BookingEntity {
    private int number;
    private CustomerEntity customer;
    private LocalDate arrivalDate;
    private LocalDateTime checkInDatetime;
    private LocalDate departureDate;
    private LocalDateTime checkOutDatetime;
    private String billingStreet;
    private String billingHouseNumber;
    private String BillingPostalCode;
    private String BillingCity;
    private String BillingCountry;
    private Set<BookedRoomCategoryEntity> bookedRoomCategories;
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

    @Id
    @Column(name = "booking_number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @ManyToOne
    @JoinColumn(name = "customer_number")
    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    @Column(name = "arrival_date")
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Column(name = "check_in_datetime")
    public LocalDateTime getCheckInDatetime() {
        return checkInDatetime;
    }

    public void setCheckInDatetime(LocalDateTime checkInDatetime) {
        this.checkInDatetime = checkInDatetime;
    }

    @Column(name = "departure_date")
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    @Column(name = "check_out_datetime")
    public LocalDateTime getCheckOutDatetime() {
        return checkOutDatetime;
    }

    public void setCheckOutDatetime(LocalDateTime checkOutDatetime) {
        this.checkOutDatetime = checkOutDatetime;
    }

    @Column(name = "billing_street")
    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    @Column(name = "billing_house_number")
    public String getBillingHouseNumber() {
        return billingHouseNumber;
    }

    public void setBillingHouseNumber(String billingHouseNumber) {
        this.billingHouseNumber = billingHouseNumber;
    }

    @Column(name = "billing_postal_code")
    public String getBillingPostalCode() {
        return BillingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        BillingPostalCode = billingPostalCode;
    }

    @Column(name = "billing_city")
    public String getBillingCity() {
        return BillingCity;
    }

    public void setBillingCity(String billingCity) {
        BillingCity = billingCity;
    }

    @Column(name = "billing_country")
    public String getBillingCountry() {
        return BillingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        BillingCountry = billingCountry;
    }

    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public Set<BookedRoomCategoryEntity> getBookedRoomCategories() {
        return bookedRoomCategories;
    }

    public void setBookedRoomCategories(Set<BookedRoomCategoryEntity> bookedRoomCategories) {
        this.bookedRoomCategories = bookedRoomCategories;
    }

    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
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