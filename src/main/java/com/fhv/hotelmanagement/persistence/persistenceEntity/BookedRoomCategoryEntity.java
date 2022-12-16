//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "booked_room_category")
public class BookedRoomCategoryEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "booking_number")
    private BookingEntity booking;

    @Id
    @ManyToOne
    @JoinColumn(name = "room_category_name")
    private RoomCategoryEntity roomCategory;

    @Column(name = "booking_price_per_night")
    private BigDecimal pricePerNight;

    @Column(name = "amount_room_category")
    private int amount;

    public BookedRoomCategoryEntity(){};

    public BookedRoomCategoryEntity(BookingEntity booking, RoomCategoryEntity roomCategory,
                                    BigDecimal pricePerNight, int amount) {
        this.booking = booking;
        this.roomCategory = roomCategory;
        this.pricePerNight = pricePerNight;
        this.amount = amount;
    }


    public BookingEntity getBooking() {
        return booking;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
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
        return "BookedRoomCategoryEntity{" +
                "booking=" + booking +
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