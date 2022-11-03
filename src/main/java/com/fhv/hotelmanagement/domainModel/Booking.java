package com.fhv.hotelmanagement.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomCategoryEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookingEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.CustomerEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Booking {

    private BookingEntity entity;
    private int number;
    private Customer customer;
    private LocalDate arrivalDate;
    private LocalDateTime checkInDatetime;
    private LocalDate departureDate;
    private LocalDateTime checkOutDatetime;
    private Address billingAddress;
    private ArrayList<BookedRoomCategory> bookedRoomCategories; //sollte eine HashMap sein
    private ArrayList<BookedRoom> bookedRooms; //sollte eine HashMap sein

    public Booking(BookingEntity entity) {
        this.number = entity.getNumber();
        this.customer = new Customer(entity.getCustomer());
        this.arrivalDate = entity.getArrivalDate();
        this.checkInDatetime = entity.getCheckInDatetime();
        this.departureDate = entity.getDepartureDate();
        this.checkOutDatetime = entity.getCheckOutDatetime();
        this.billingAddress = new Address(entity.getBillingStreet(), entity.getBillingHouseNumber(), entity.getBillingPostalCode(), entity.getBillingCity(), entity.getBillingCountry());
        //this.bookedRoomCategories = entity.getBookedRoomCategories();
        //this.bookedRooms = entity.getBookedRooms();
    }

    public BookingEntity getEntity(){
        return entity;
    }
}
