package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;

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

    public ArrayList<Board> getAllBoards() {
        return boards;
    }

    public ArrayList<BookedRoomDTO> getAllBookedRooms() {
        ArrayList<BookedRoom> bookedRooms = PersistenceFacade.getAllBookedRooms();
        ArrayList<BookedRoomDTO> bookedRoomDTOS = new ArrayList<>();
        for (BookedRoom r : bookedRooms) {
            bookedRoomDTOS.add(DomainTranslator.translateBookedRoom(r));
        }
        return bookedRoomDTOS;
    }

    public ArrayList<Room> getAllRooms() {
        return rooms;
    }

    public ArrayList<RoomCategory> getAllRoomCategories() {
        return roomCategories;
    }
}
