package com.fhv.hotelmanagement.domainModel;

public class Room {
    private int number;
    private RoomCategory category;

    public Room(int number, RoomCategory category) {
        this.number = number;
        this.category = category;
    }
}
