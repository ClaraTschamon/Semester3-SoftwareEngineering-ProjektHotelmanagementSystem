package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.dataMapper.BoardDataMapper;
import com.fhv.hotelmanagement.persistence.dataMapper.RoomCategoryDataMapper;
import com.fhv.hotelmanagement.persistence.dataMapper.RoomDataMapper;

import java.util.ArrayList;

public class DomainManager {
    ArrayList<Board> boards;
    ArrayList<Room> rooms;
    ArrayList<RoomCategory> roomCategories;

    public DomainManager() {
        boards = BoardDataMapper.getAll();
        rooms = RoomDataMapper.getAll();
        roomCategories = RoomCategoryDataMapper.getAll();
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
