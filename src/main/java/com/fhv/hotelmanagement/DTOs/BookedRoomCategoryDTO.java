package com.fhv.hotelmanagement.DTOs;

import java.math.BigDecimal;

public class BookedRoomCategoryDTO {
    private BookingDTO booking;
    private RoomCategoryDTO roomCategory;
    private BigDecimal pricePerNight;
    private int amount;

    public BookedRoomCategoryDTO() {}
}
