package com.fhv.hotelmanagement.DBModel;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "booked_room")
public class BookedRoom {
    private Booking booking;
    private Room room;
    private LocalDate fromDate;
    private LocalDate toDate;

    @Id
    @ManyToOne
    @JoinColumn(name = "booking_number")
    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Id
    @ManyToOne
    @JoinColumn (name = "room_number")
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Column (name = "from_date")
    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    @Column (name = "to_date")
    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
