package com.fhv.hotelmanagement.view.DTOs;

import java.time.LocalDate;

public class BookedRoomDTO {
    private BookingDTO booking;
    private RoomDTO room;
    private LocalDate fromDate;
    private LocalDate toDate;

    public BookedRoomDTO() {}

    public BookedRoomDTO(BookingDTO booking, RoomDTO room, LocalDate fromDate, LocalDate toDate) {
        this.booking = booking;
        this.room = room;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

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
