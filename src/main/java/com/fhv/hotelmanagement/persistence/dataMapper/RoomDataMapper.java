package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domainModel.Room;
import com.fhv.hotelmanagement.domainModel.RoomCategory;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomCategoryEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomEntity;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDataMapper {
    private RoomDataMapper(){}

    private static RoomDataMapper _instance = new RoomDataMapper();

    public static RoomDataMapper _instance(){
        return _instance;
    }

    //read
    public Optional<Room> get(final int number){
        RoomEntity entity = PersistenceFacade.instance().entityManager.find(RoomEntity.class, number);
        if(entity != null){
            Room room = new Room(entity);
            return Optional.of(room);
        }
        return Optional.empty();
    }

    public static ArrayList<Room> getAll(){
        ArrayList<RoomEntity> entities = (ArrayList<RoomEntity>) PersistenceFacade.instance().entityManager.createQuery("from RoomEntity").getResultList();
        ArrayList<Room> rooms = new ArrayList<>();
        for(RoomEntity e : entities){
            rooms.add(new Room(e));
        }
        return rooms;
    }

    //update
    public void store(Room room){
        PersistenceFacade.instance().entityManager.merge(room.getEntity());
    }
}
