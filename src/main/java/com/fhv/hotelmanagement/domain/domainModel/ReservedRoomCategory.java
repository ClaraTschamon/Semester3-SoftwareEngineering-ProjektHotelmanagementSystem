//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.domainModel;

import java.math.BigDecimal;

public class ReservedRoomCategory {
    private Reservation reservation;
    private RoomCategory roomCategory;
    private BigDecimal pricePerNight;
    private int amount;

    public ReservedRoomCategory(Reservation reservation, RoomCategory roomCategory, BigDecimal pricePerNight, int amount){
        this.reservation = reservation;
        this.roomCategory = roomCategory;
        this.pricePerNight = pricePerNight;
        this.amount = amount;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public int getAmount() {
        return amount;
    }
}
