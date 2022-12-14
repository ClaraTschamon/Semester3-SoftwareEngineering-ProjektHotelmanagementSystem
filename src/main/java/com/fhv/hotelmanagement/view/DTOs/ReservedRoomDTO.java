//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.DTOs;

import java.time.LocalDate;

public class ReservedRoomDTO {
    private ReservationDTO reservation;
    private RoomDTO room;
    private LocalDate fromDate;
    private LocalDate toDate;

    public ReservedRoomDTO() {}

    public ReservedRoomDTO(ReservationDTO reservation, RoomDTO room, LocalDate fromDate, LocalDate toDate) {
        this.reservation = reservation;
        this.room = room;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
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

    @Override
    public String toString(){
        return String.valueOf(room.getNumber());
    }
}
