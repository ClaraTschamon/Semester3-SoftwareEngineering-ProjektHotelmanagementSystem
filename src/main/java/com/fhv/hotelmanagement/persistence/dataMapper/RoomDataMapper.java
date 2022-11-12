package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Room;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomCategoryEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomEntity;

import java.util.ArrayList;
import java.util.Optional;

public class RoomDataMapper {
    private RoomDataMapper(){}

    private static RoomDataMapper _instance = new RoomDataMapper();

    public static RoomDataMapper instance(){
        return _instance;
    }

    //read
    public Optional<Room> get(final int number){
        RoomEntity entity = PersistenceFacade.instance().entityManager.find(RoomEntity.class, number);
        if(entity != null){
            Room room = createRoom(entity);
            return Optional.of(room);
        }
        return Optional.empty();
    }

    public static ArrayList<Room> getAll(){
        ArrayList<RoomEntity> entities = (ArrayList<RoomEntity>) PersistenceFacade.instance().entityManager.createQuery("from RoomEntity").getResultList();
        ArrayList<Room> rooms = new ArrayList<>();
        for(RoomEntity e : entities){
            rooms.add(createRoom(e));
        }
        return rooms;
    }

    //update
    public void store(Room room){
        PersistenceFacade.instance().entityManager.merge(createRoomEntity(room));
    }

    protected static RoomEntity createRoomEntity(Room room) {
        return new RoomEntity(room.getNumber(), room.getIsFree(), room.getIsClean(),
                RoomCategoryDataMapper.createRoomCategoryEntity(room.getCategory()));
    }

    protected static Room createRoom(RoomEntity roomEntity) {
        return new Room(roomEntity.getNumber(), roomEntity.getIsFree(), roomEntity.getIsFree(),
                RoomCategoryDataMapper.createRoomCategory(roomEntity.getCategory()));
    }
}
