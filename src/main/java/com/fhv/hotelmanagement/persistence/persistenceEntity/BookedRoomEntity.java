package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "booked_room")
public class BookedRoomEntity {
    private BookingEntity booking;
    private RoomEntity room;
    private LocalDate fromDate;
    private LocalDate toDate;

    public BookedRoomEntity(){};

    public BookedRoomEntity(BookingEntity booking, RoomEntity room,
                            LocalDate fromDate, LocalDate toDate) {
        this.booking = booking;
        this.room = room;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "booking_number")
    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "room_number")
    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    @Column(name = "from_date")
    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    @Column(name = "to_date")
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
