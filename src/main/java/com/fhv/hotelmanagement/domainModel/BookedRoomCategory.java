package com.fhv.hotelmanagement.domainModel;

import java.math.BigDecimal;

public class BookedRoomCategory {
    private Booking booking;
    private RoomCategory roomCategory;
    private BigDecimal pricePerNight;
    private int amount;

    public BookedRoomCategory(Booking booking, RoomCategory roomCategory, BigDecimal pricePerNight, int amount) {
        this.booking = booking;
        this.roomCategory = roomCategory;
        this.pricePerNight = pricePerNight;
        this.amount = amount;
    }
}
