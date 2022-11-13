package com.fhv.hotelmanagement.view.DTOs;

import java.time.LocalDate;

public class BookedRoomDTO {
    private BookingDTO booking;
    private RoomDTO room;
    private LocalDate fromDate;
    private LocalDate toDate;

    public BookedRoomDTO() {}

    public BookingDTO getBooking() {
        return booking;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }
}
