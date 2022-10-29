package com.fhv.hotelmanagement.domainModel;

import java.time.LocalDate;

public class BookedRoom {
    private Booking booking;
    private Room room;
    private LocalDate fromDate;
    private LocalDate toDate;

    public BookedRoom(Booking booking, Room room, LocalDate fromDate, LocalDate toDate) {
        this.booking = booking;
        this.room = room;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
}
