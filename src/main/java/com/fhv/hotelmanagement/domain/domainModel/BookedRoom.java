//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.domainModel;

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

    public Room getRoom() {
        return room;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        if (this.booking == null) {
            this.booking = booking;
        }
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }
}
