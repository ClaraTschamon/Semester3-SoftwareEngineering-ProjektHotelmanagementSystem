//Hotelmanagementsystem TeamA 2022/23
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

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
