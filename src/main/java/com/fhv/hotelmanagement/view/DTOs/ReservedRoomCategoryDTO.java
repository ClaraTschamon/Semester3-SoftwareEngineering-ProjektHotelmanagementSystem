//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.DTOs;

import java.math.BigDecimal;

public class ReservedRoomCategoryDTO {
    private ReservationDTO reservation;
    private RoomCategoryDTO roomCategory;
    private BigDecimal pricePerNight;
    private int amount;

    public ReservedRoomCategoryDTO() {}

    public ReservedRoomCategoryDTO(ReservationDTO reservation, RoomCategoryDTO roomCategory, BigDecimal pricePerNight, int amount){
        this.reservation = reservation;
        this.roomCategory = roomCategory;
        this.pricePerNight = pricePerNight;
        this.amount = amount;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }

    public RoomCategoryDTO getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategoryDTO roomCategory) {
        this.roomCategory = roomCategory;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
