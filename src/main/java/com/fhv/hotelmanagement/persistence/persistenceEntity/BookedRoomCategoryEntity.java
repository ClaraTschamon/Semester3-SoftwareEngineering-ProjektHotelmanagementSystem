package com.fhv.hotelmanagement.persistence.persistenceEntity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "booked_room_category")
public class BookedRoomCategoryEntity {

    private BookingEntity booking;
    private RoomCategoryEntity roomCategory;
    private BigDecimal pricePerNight;
    private int amount;

    public BookedRoomCategoryEntity(){};

    public BookedRoomCategoryEntity(BookingEntity booking, RoomCategoryEntity roomCategory,
                                    BigDecimal pricePerNight, int amount) {
        this.booking = booking;
        this.roomCategory = roomCategory;
        this.pricePerNight = pricePerNight;
        this.amount = amount;
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
    @JoinColumn(name = "room_category_name")
    public RoomCategoryEntity getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategoryEntity roomCategory) {
        this.roomCategory = roomCategory;
    }

    @Column(name = "booking_price_per_night")
    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Column(name = "amount_room_category")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}