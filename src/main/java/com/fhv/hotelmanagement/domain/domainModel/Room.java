package com.fhv.hotelmanagement.domain.domainModel;

//TODO: Preis pro Nacht fehlt
public class Room {
    private int number;

    private Boolean isFree;

    private Boolean isClean;
    private RoomCategory category;

    public Room(int number, boolean isFree, boolean isClean, RoomCategory category) {
        this.number = number;
        this.isFree = isFree;
        this.isClean = isClean;
        this.category = category;
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
