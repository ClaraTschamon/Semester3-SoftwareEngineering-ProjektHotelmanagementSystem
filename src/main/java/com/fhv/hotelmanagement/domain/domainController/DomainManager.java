package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import com.fhv.hotelmanagement.view.DTOs.RoomDTO;

import java.util.ArrayList;

public class DomainManager {
    ArrayList<Board> boards;
    ArrayList<RoomCategory> roomCategories;

    public DomainManager() {
        boards = PersistenceFacade.getAllBoards();
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

    public ArrayList<RoomDTO> getAllRooms() {
        ArrayList<Room> rooms = PersistenceFacade.getAllRooms();
        ArrayList<RoomDTO> roomDTOS = new ArrayList<>();
        for(Room r : rooms){
            roomDTOS.add(DomainTranslator.translateRoom(r));
        }
        return roomDTOS;
    }

    public ArrayList<RoomCategory> getAllRoomCategories() {
        return roomCategories;
    }
}
