package com.fhv.hotelmanagement.domain.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomEntity;
//TODO: Preis pro Nacht fehlt
public class Room {

    private RoomEntity entity;
    private int number;

    private Boolean isFree;

    private Boolean isClean;
    private RoomCategory category;

    public Room(RoomEntity entity) {
        this.entity = entity;
        this.number = entity.getNumber();
        this.isFree = entity.getIsFree();
        this.isClean = entity.getIsClean();
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

    public boolean getIsFree(){
        return  isFree;
    }

    public boolean getIsClean(){
        return isClean;
    }
}
