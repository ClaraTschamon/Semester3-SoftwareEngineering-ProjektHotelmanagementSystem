package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.Room;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.RoomDTO;

import java.util.ArrayList;

public class RoomFactory {
    private static ArrayList<Room> rooms;

    public static ArrayList<RoomDTO> getAllRooms() {
        if (rooms == null) {
            refreshRooms();
        }

        ArrayList<RoomDTO> roomDTOs = new ArrayList<RoomDTO>();
        for (Room r : rooms) {
            roomDTOs.add(createRoomDTO(r));
        }
        return roomDTOs;
    }

    private static void refreshRooms() {
        rooms = new ArrayList<Room>();
        for (Room r : PersistenceFacade.getAllRooms()) {
            rooms.add(r);
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
