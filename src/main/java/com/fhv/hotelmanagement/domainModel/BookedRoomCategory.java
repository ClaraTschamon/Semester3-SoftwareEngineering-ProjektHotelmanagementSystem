package com.fhv.hotelmanagement.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomCategoryEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookingEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomCategoryEntity;

import java.math.BigDecimal;

public class BookedRoomCategory {

    private BookedRoomCategoryEntity entity;
    private BookingEntity booking;
    private RoomCategory roomCategory;
    private BigDecimal pricePerNight;
    private int amount;

    public BookedRoomCategory(BookedRoomCategoryEntity entity) {
        this.entity = entity;
        this.booking = entity.getBooking();
        this.roomCategory = new RoomCategory(entity.getRoomCategory());
        this.pricePerNight = entity.getPricePerNight();
        this.amount = entity.getAmount();
    }

    public BookedRoomCategoryEntity getEntity(){
        return entity;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }
}
