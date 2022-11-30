//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "booked_room")
public class BookedRoomEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "booking_number")
    private BookingEntity booking;

    @Id
    @ManyToOne
    @JoinColumn(name = "room_number")
    private RoomEntity room;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    public BookedRoomEntity(){};

    public BookedRoomEntity(BookingEntity booking, RoomEntity room,
                            LocalDate fromDate, LocalDate toDate) {
        this.booking = booking;
        this.room = room;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }


    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
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

    @Override
    public String toString() {
        return "BookedRoomEntity{" +
                "booking=" + booking +
                ", room=" + room +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }





}
