package com.fhv.hotelmanagement.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomEntity;

public class Room {

    private RoomEntity entity;
    private int number;
    private RoomCategory category;

    public Room(RoomEntity entity) {
        this.entity = entity;
        this.number = entity.getNumber();
        this.category = new RoomCategory(entity.getCategory());
    }

    public RoomEntity getEntity(){
        return entity;
    }

    public int getNumber() {
        return number;
    }

    public RoomCategory getCategory(){
        return category;
    }
}
