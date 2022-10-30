package com.fhv.hotelmanagement.JPAPersistence;

import com.fhv.hotelmanagement.DBModel.Room;
import com.fhv.hotelmanagement.DBModel.RoomCategory;

import java.util.List;

public class DBFacade {

    public List<RoomCategory> getAllRoomCategories(){
        return new BrokerRoomCategory().getAll();
    }

    public void insertRoomCategories(RoomCategory rc){
        new BrokerRoomCategory().insert(rc);
    }

    public void removeRoomCategories(RoomCategory rc){
        new BrokerRoomCategory().delete(rc);
    }

    public void updateRoomCategories(RoomCategory rc){
        new BrokerRoomCategory().update(rc);
    }


    public List<Room> getAllRooms(){
        return new BrokerRoom().getAll();
    }

    public void insertRoom(Room r){
        new BrokerRoom().insert(r);
    }

    public void removeRoom(Room r){
        new BrokerRoom().delete(r);
    }

    public void updateRoom(Room r){
        new BrokerRoom().update(r);
    }
}
