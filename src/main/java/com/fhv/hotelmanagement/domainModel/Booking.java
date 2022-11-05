package com.fhv.hotelmanagement.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.*;

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
    private HashMap<RoomCategory, BookedRoomCategory> bookedRoomCategories;
    private HashMap<Room, BookedRoom> bookedRooms;

    public Booking(BookingEntity entity) {
        this.number = entity.getNumber();
        this.customer = new Customer(entity.getCustomer());
        this.arrivalDate = entity.getArrivalDate();
        this.checkInDatetime = entity.getCheckInDatetime();
        this.departureDate = entity.getDepartureDate();
        this.checkOutDatetime = entity.getCheckOutDatetime();
        this.billingAddress = new Address(entity.getBillingStreet(), entity.getBillingHouseNumber(), entity.getBillingPostalCode(), entity.getBillingCity(), entity.getBillingCountry());

        this.bookedRoomCategories = new HashMap<>();
        for (BookedRoomCategoryEntity e : entity.getBookedRoomCategories()) {
            BookedRoomCategory c = new BookedRoomCategory(e);
            this.bookedRoomCategories.put(c.getRoomCategory(), c);
        }
        this.bookedRooms = new HashMap<>();
        for (BookedRoomEntity e : entity.getBookedRooms()) {
            BookedRoom r = new BookedRoom(e);
            this.bookedRooms.put(r.getRoom(), r);
        }
    }

    public BookingEntity getEntity(){
        return entity;
    }
}
