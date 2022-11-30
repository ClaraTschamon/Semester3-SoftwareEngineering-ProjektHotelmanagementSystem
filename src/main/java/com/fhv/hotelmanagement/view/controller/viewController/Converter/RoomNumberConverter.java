//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.view.controller.viewController.Converter;

import com.fhv.hotelmanagement.view.DTOs.RoomDTO;
import javafx.util.StringConverter;

import java.util.ArrayList;

public class RoomNumberConverter<T> extends StringConverter<RoomDTO> {
    ArrayList<RoomDTO> rooms;

    public RoomNumberConverter(ArrayList<RoomDTO> rooms){
        this.rooms = rooms;
    }

    @Override
    public RoomDTO fromString(final String number) {
        for (RoomDTO roomDTO : rooms) {
            if (String.valueOf(roomDTO.getNumber()).equals(number)) {
                return roomDTO;
            }
        }
        return null;
    }

    @Override
    public String toString(final RoomDTO room) {
        if (room == null) {
            return null;
        }
        return String.valueOf(room.getNumber());
    }
}
