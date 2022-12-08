//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.domainModel;

import java.time.LocalDate;

public class ReservedRoom {
    private Reservation reservation;
    private Room room;
    private LocalDate fromDate;
    private LocalDate toDate;

    public ReservedRoom(Reservation reservation, Room room, LocalDate fromDate, LocalDate toDate) {
        this.reservation = reservation;
        this.room = room;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        if(this.reservation == null){
            this.reservation = reservation;
        }
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
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
