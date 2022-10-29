package com.fhv.hotelmanagement.domainModel;

import com.fhv.hotelmanagement.DBModel.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Booking {
    private int number;
    private Customer customer;
    private LocalDate arrivalDate;
    private LocalDateTime checkInDatetime;
    private LocalDate departureDate;
    private LocalDateTime checkOutDatetime;
    private Address billingAddress;
    private HashMap<RoomCategory, BookedRoomCategory> bookedRoomCategories;
    private HashMap<Room, BookedRoom> bookedRooms;

    public Booking(int number, Customer customer, LocalDate arrivalDate, LocalDateTime checkInDatetime,
                   LocalDate departureDate, LocalDateTime checkOutDatetime, Address billingAddress,
                   HashMap<RoomCategory, BookedRoomCategory> bookedRoomCategories, HashMap<Room, BookedRoom> bookedRooms) {
        this.number = number;
        this.customer = customer;
        this.arrivalDate = arrivalDate;
        this.checkInDatetime = checkInDatetime;
        this.departureDate = departureDate;
        this.checkOutDatetime = checkOutDatetime;
        this.billingAddress = billingAddress;
        this.bookedRoomCategories = bookedRoomCategories;
        this.bookedRooms = bookedRooms;
    }
}
