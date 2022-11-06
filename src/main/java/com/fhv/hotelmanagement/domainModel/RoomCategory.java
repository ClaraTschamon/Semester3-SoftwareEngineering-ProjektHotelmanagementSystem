package com.fhv.hotelmanagement.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomCategoryEntity;

import java.math.BigDecimal;

public class RoomCategory {

    private RoomCategoryEntity entity;
    private String name;
    private BigDecimal pricePerNight;

    public RoomCategory(RoomCategoryEntity entity) {
        this.entity = entity;
        this.name = entity.getName();
        this.pricePerNight = entity.getPricePerNight();
    }

    public RoomCategoryEntity getEntity(){
        return entity;
    }

    public String getName(){
        return name;
    }
}
