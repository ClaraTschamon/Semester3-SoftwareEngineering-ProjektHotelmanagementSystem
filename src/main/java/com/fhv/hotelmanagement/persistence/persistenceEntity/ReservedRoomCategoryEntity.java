package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name="reserved_room_category")
public class ReservedRoomCategoryEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "reservation_number")
    private ReservationEntity reservation;

    @Id
    @ManyToOne
    @JoinColumn(name = "room_category_name")
    private RoomCategoryEntity roomCategory;

    @Column(name = "booking_price_per_night")
    private BigDecimal pricePerNight;

    @Column(name = "amount_room_category")
    private int amount;

    public ReservedRoomCategoryEntity(){};

    public ReservedRoomCategoryEntity(ReservationEntity reservation, RoomCategoryEntity roomCategory,
                                      BigDecimal pricePerNight, int amount){
        this.reservation = reservation;
        this.roomCategory = roomCategory;
        this.pricePerNight = pricePerNight;
        this.amount = amount;
    }

    public ReservationEntity getReservation() {
        return reservation;
    }

    public void setReservation(ReservationEntity reservation) {
        this.reservation = reservation;
    }

    public RoomCategoryEntity getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategoryEntity roomCategory) {
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

    @Override
    public String toString() {
        return "ReservedRoomCategoryEntity{" +
                "reservation=" + reservation +
                ", roomCategory=" + roomCategory +
                ", pricePerNight=" + pricePerNight +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
