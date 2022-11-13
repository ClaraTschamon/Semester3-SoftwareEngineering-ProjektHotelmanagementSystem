package com.fhv.hotelmanagement.view.DTOs;

import java.math.BigDecimal;

public class BookedRoomCategoryDTO {
    private BookingDTO booking;
    private RoomCategoryDTO roomCategory;
    private BigDecimal pricePerNight;
    private int amount;

    public BookedRoomCategoryDTO() {}

    public BookedRoomCategoryDTO(BookingDTO booking, RoomCategoryDTO roomCategory, BigDecimal pricePerNight, int amount) {
        this.booking = booking;
        this.roomCategory = roomCategory;
        this.pricePerNight = pricePerNight;
        this.amount = amount;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public RoomCategoryDTO getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategoryDTO roomCategory) {
        this.roomCategory = roomCategory;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
