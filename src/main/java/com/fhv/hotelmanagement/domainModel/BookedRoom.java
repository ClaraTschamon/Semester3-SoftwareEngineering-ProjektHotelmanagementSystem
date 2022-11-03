package com.fhv.hotelmanagement.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomEntity;

import java.time.LocalDate;

public class BookedRoom {

    private BookedRoomEntity entity;
    private Booking booking;
    private Room room;
    private LocalDate fromDate;
    private LocalDate toDate;

    public BookedRoom(BookedRoomEntity entity) {
        this.entity = entity;
        this.booking = new Booking(entity.getBooking());
        this.room = new Room(entity.getRoom());
        this.fromDate = entity.getFromDate();
        this.toDate = entity.getToDate();
    }

    public BookedRoomEntity getEntity(){
        return entity;
    }
}
