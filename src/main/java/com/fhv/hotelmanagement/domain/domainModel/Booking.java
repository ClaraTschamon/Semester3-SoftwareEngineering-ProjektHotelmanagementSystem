package com.fhv.hotelmanagement.domain.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Booking {

    private static int id;
    private Integer number;
    private Customer customer;
    private LocalDate arrivalDate;
    private LocalDateTime checkInDatetime;
    private LocalDate departureDate;
    private LocalDateTime checkOutDatetime;
    private Address billingAddress;

    private String paymentMethod;

    private String creditCardNumber;

    private LocalDate expirationDate;

    private String authorisationNumber;
    private String comment;
    private HashMap<RoomCategory, BookedRoomCategory> bookedRoomCategories;
    private HashMap<Room, BookedRoom> bookedRooms;

    public Booking() {
        id++;
    }

    public Booking(Integer number, Customer customer, LocalDate arrivalDate, LocalDateTime checkInDatetime, LocalDate departureDate,
                   LocalDateTime checkOutDatetime, String billingStreet, String billingHouseNumber, String billingPostalCode,
                   String billingCity, String billingCountry, HashMap<RoomCategory, BookedRoomCategory> bookedRoomCategories,
                   HashMap<Room, BookedRoom> bookedRooms) {
        if (number.equals(null)) {
            number = getId();
        }
        this.number = number;
        this.customer = customer;
        this.arrivalDate = arrivalDate;
        this.checkInDatetime = checkInDatetime;
        this.departureDate = departureDate;
        this.checkOutDatetime = checkOutDatetime;
        this.billingAddress = new Address(billingStreet, billingHouseNumber, billingPostalCode, billingCity, billingCountry);
        this.bookedRoomCategories = bookedRoomCategories;
        this.bookedRooms = bookedRooms;
        id++;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public HashMap<RoomCategory, BookedRoomCategory> getBookedRoomCategories() {
        return bookedRoomCategories;
    }

    public void setBookedRoomCategories(HashMap<RoomCategory, BookedRoomCategory> bookedRoomCategories) {
        this.bookedRoomCategories = bookedRoomCategories;
    }

    public HashMap<Room, BookedRoom> getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(HashMap<Room, BookedRoom> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    //id braucht nur getter weil sie statisch zugeteilt wird
    public static int getId() {
        return id;
    }
}
