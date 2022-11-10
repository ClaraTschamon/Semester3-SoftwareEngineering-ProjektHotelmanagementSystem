package com.fhv.hotelmanagement.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Booking {

    private static int id;

    private BookingEntity entity;
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

    public Booking(BookingEntity entity) {
        this.number = entity.getNumber();
        this.customer = new Customer(entity.getCustomer());
        this.arrivalDate = entity.getArrivalDate();
        this.checkInDatetime = entity.getCheckInDatetime();
        this.departureDate = entity.getDepartureDate();
        this.checkOutDatetime = entity.getCheckOutDatetime();
        this.billingAddress = new Address(entity.getBillingStreet(), entity.getBillingHouseNumber(), entity.getBillingPostalCode(), entity.getBillingCity(), entity.getBillingCountry());

        this.bookedRoomCategories = new HashMap<>();
        if(entity.getBookedRoomCategories() != null) {
            for (BookedRoomCategoryEntity e : entity.getBookedRoomCategories()) {
                BookedRoomCategory c = new BookedRoomCategory(e);
                this.bookedRoomCategories.put(c.getRoomCategory(), c);
            }
        }
        this.bookedRooms = new HashMap<>();
        if(entity.getBookedRooms() != null) {
            for (BookedRoomEntity e : entity.getBookedRooms()) {
                BookedRoom r = new BookedRoom(e);
                this.bookedRooms.put(r.getRoom(), r);
            }
        }
    }

    public BookingEntity getEntity(){
        return entity;
    }

    public void setEntity(BookingEntity entity) {
        this.entity = entity;
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
