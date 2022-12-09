//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="reserved_room")
public class ReservedRoomEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "reservation_number")
    private ReservationEntity reservation;

    @Id
    @ManyToOne
    @JoinColumn(name = "room_number")
    private RoomEntity room;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    public ReservedRoomEntity(){};

    public ReservedRoomEntity(ReservationEntity reservation, RoomEntity room,
                            LocalDate fromDate, LocalDate toDate) {
        this.reservation = reservation;
        this.room = room;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public ReservationEntity getReservation() {
        return reservation;
    }

    public void setReservation(ReservationEntity reservation) {
        this.reservation = reservation;
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
        return "ReservedRoomEntity{" +
                "reservation=" + reservation +
                ", room=" + room +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
