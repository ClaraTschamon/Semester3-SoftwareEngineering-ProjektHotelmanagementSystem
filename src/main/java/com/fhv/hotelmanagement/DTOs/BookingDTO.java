package com.fhv.hotelmanagement.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

public class BookingDTO {
    private Integer number;
    private CustomerDTO customer;
    private LocalDate arrivalDate;
    private LocalDateTime checkInDatetime;
    private LocalDate departureDate;
    private LocalDateTime checkOutDatetime;
    private AddressDTO billingAddress;
    private HashMap<RoomCategoryDTO, BookedRoomCategoryDTO> bookedRoomCategories;
    private HashMap<RoomDTO, BookedRoomDTO> bookedRooms;

    public BookingDTO() {}

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
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

    public AddressDTO getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressDTO billingAddress) {
        this.billingAddress = billingAddress;
    }

    public HashMap<RoomCategoryDTO, BookedRoomCategoryDTO> getBookedRoomCategories() {
        return bookedRoomCategories;
    }

    public void setBookedRoomCategories(HashMap<RoomCategoryDTO, BookedRoomCategoryDTO> bookedRoomCategories) {
        this.bookedRoomCategories = bookedRoomCategories;
    }

    public HashMap<RoomDTO, BookedRoomDTO> getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(HashMap<RoomDTO, BookedRoomDTO> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }
}
