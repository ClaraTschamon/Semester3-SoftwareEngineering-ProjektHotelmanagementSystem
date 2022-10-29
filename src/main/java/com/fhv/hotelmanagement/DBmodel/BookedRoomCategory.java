package com.fhv.hotelmanagement.DBmodel;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "booked_room_category")
public class BookedRoomCategory {
    private Booking booking;
    private RoomCategory roomCategory;
    private BigDecimal pricePerNight;
    private int amount;

    @Id
    @ManyToOne
    @JoinColumn (name = "booking_number")
    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Id
    @ManyToOne
    @JoinColumn (name = "room_category_name")
    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    @Column (name = "booking_price_per_night")
    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Column (name = "amount_room_category")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
