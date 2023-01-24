//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.RoomCategory;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.view.DTOs.RoomCategoryDTO;

import java.util.HashMap;

public class RoomCategoryFactory {
    private static HashMap<String, RoomCategory> roomCategories;

    public static HashMap<String, RoomCategoryDTO> getAllRoomCategories() {
        if (roomCategories == null) {
            refreshRoomCategories();
        }

        HashMap<String, RoomCategoryDTO> roomCategoryDTOs = new HashMap<>();
        for (RoomCategory c : roomCategories.values()) {
            roomCategoryDTOs.put(c.getName(), createRoomCategoryDTO(c));
        }
        return roomCategoryDTOs;
    }

    private static void refreshRoomCategories() {
        roomCategories = new HashMap<>();
        for (RoomCategory c : PersistenceFacade.getAllRoomCategories()) {
            roomCategories.put(c.getName(), c);
        }
    }

    protected static RoomCategoryDTO createRoomCategoryDTO(RoomCategory roomCategory) {
        return new RoomCategoryDTO(roomCategory.getName(), roomCategory.getPricePerNight());
    }

    protected static RoomCategory createRoomCategory(RoomCategoryDTO roomCategoryDTO) {
        return new RoomCategory(roomCategoryDTO.getName(), roomCategoryDTO.getPricePerNight());
    }
}
