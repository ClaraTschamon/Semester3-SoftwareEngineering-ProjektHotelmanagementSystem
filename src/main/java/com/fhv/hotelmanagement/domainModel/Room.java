package com.fhv.hotelmanagement.domainModel;

import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomEntity;
//TODO: Preis pro Nacht fehlt
public class Room {

    private RoomEntity entity;
    private int number;
    private RoomCategory category;

    private Boolean isFree; //TODO: in entity und in datenbank!

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

    public boolean getIsFree(){
        return  isFree;
    }
}
