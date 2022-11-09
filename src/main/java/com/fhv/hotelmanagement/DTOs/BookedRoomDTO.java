package com.fhv.hotelmanagement.DTOs;

import java.time.LocalDate;

public class BookedRoomDTO {
    private BookingDTO booking;
    private RoomDTO room;
    private LocalDate fromDate;
    private LocalDate toDate;

    public BookedRoomDTO() {}
}
