package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.Room;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.RoomDTO;

import java.util.ArrayList;

public class RoomFactory {
    private static ArrayList<RoomDTO> rooms;

    public static ArrayList<RoomDTO> getAllRooms() {
        if (rooms == null) {
            refreshRooms();
        }
        return rooms;
    }

    private static void refreshRooms() {
        rooms = new ArrayList<RoomDTO>();
        for (Room r : PersistenceFacade.getAllRooms()) {
            rooms.add(createRoomDTO(r));
        }
    }

    protected static RoomDTO createRoomDTO(Room room) {
        return new RoomDTO(room.getNumber(), room.isFree(), true, //TODO when implementing clean
                RoomCategoryFactory.createRoomCategoryDTO(room.getCategory()));
    }

    protected static Room createRoom(RoomDTO roomDTO) {
        return new Room(roomDTO.getNumber(), RoomCategoryFactory.createRoomCategory(roomDTO.getCategory()));
    }
}
