package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;

import java.util.ArrayList;

public class DomainManager {
    ArrayList<Board> boards;
    ArrayList<Room> rooms;
    ArrayList<RoomCategory> roomCategories;

    public DomainManager() {
        boards = PersistenceFacade.getAllBoards();
        rooms = PersistenceFacade.getAllRooms();
        roomCategories = PersistenceFacade.getAllRoomCategories();
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<RoomCategory> getRoomCategories() {
        return roomCategories;
    }
}
