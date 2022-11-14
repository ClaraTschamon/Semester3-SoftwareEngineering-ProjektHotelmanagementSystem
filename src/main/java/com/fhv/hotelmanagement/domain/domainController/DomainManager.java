package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DomainManager {
    ArrayList<Board> boards;
    ArrayList<Room> rooms;
    ArrayList<RoomCategory> roomCategories;

    public DomainManager() {
        refreshBoards();
        refreshRooms();
        refreshRoomCategories();
    }

    public void refreshBoards() {
        boards = PersistenceFacade.getAllBoards();
    }

    public void refreshRooms() {
        rooms = PersistenceFacade.getAllRooms();
    }

    public void refreshRoomCategories() {
        roomCategories = PersistenceFacade.getAllRoomCategories();
    }

    public ArrayList<BookedRoomDTO> getAllBookedRoomDTOs() {
        ArrayList<BookedRoom> bookedRooms = PersistenceFacade.getAllBookedRooms();
        ArrayList<BookedRoomDTO> bookedRoomDTOS = new ArrayList<>();
        for (BookedRoom r : bookedRooms) {
            bookedRoomDTOS.add(DomainTranslator.translateBookedRoom(r));
        }
        return bookedRoomDTOS;
    }

    public ArrayList<RoomDTO> getAllRoomDTOs() {
        ArrayList<RoomDTO> roomDTOS = new ArrayList<RoomDTO>();
        for (Room r : rooms) {
            roomDTOS.add(DomainTranslator.translateRoom(r));
        }
        return roomDTOS;
    }

    public HashMap<String, RoomCategoryDTO> getAllRoomCategoryDTOs() {
        HashMap<String, RoomCategoryDTO> roomCategoryDTOs = new HashMap<>();
        for (RoomCategory c : roomCategories) {
            roomCategoryDTOs.put(c.getName(), DomainTranslator.translateRoomCategory(c));
        }
        return roomCategoryDTOs;
    }
}
