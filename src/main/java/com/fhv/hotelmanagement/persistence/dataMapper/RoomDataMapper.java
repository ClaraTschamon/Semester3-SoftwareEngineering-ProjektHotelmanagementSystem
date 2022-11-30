//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.Room;
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
        RoomEntity entity = PersistenceManager.instance().entityManager.find(RoomEntity.class, number);
        if(entity != null){
            Room room = createRoom(entity);
            return Optional.of(room);
        }
        return Optional.empty();
    }

    public static ArrayList<Room> getAll(){
        ArrayList<RoomEntity> entities = (ArrayList<RoomEntity>) PersistenceManager.instance().entityManager.createQuery("from RoomEntity").getResultList();
        ArrayList<Room> rooms = new ArrayList<>();
        for(RoomEntity e : entities){
            rooms.add(createRoom(e));
        }
        return rooms;
    }

    //update
    public void store(Room room){
        var entityManager = PersistenceManager.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.merge(createRoomEntity(room));
        entityManager.getTransaction().commit();
    }

    protected static RoomEntity createRoomEntity(Room room) {
        return new RoomEntity(room.getNumber(),
                RoomCategoryDataMapper.createRoomCategoryEntity(room.getCategory()));
    }

    protected static Room createRoom(RoomEntity roomEntity) {
        return new Room(roomEntity.getNumber(), RoomCategoryDataMapper.createRoomCategory(roomEntity.getCategory()));
    }
}
