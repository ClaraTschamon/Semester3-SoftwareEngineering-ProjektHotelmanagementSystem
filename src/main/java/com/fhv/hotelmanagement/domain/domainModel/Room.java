package com.fhv.hotelmanagement.domain.domainModel;

public class Room {
    private Integer number;
    private RoomCategory category;

    public Room(Integer number, RoomCategory category) {
        this.number = number;
        this.category = category;
    }

    public Integer getNumber() {
        return number;
    }

    public RoomCategory getCategory(){
        return category;
    }

}
