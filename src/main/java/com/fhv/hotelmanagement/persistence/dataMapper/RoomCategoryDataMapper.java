package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domainModel.BookedRoomCategory;
import com.fhv.hotelmanagement.domainModel.Booking;
import com.fhv.hotelmanagement.domainModel.RoomCategory;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomCategoryEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomCategoryEntity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;


public class RoomCategoryDataMapper {
    private RoomCategoryDataMapper(){}

    private static RoomCategoryDataMapper _instance = new RoomCategoryDataMapper();

    public static RoomCategoryDataMapper instance(){
        return _instance;
    }

    //read
    public Optional<RoomCategory> get(final String name){
        RoomCategoryEntity entity = PersistenceFacade.instance().entityManager.find(RoomCategoryEntity.class, name);
        if(entity != null){
            RoomCategory roomCategory = new RoomCategory(entity);
            return Optional.of(roomCategory);
        }
        return Optional.empty();
    }

    //create
    public void insert(RoomCategory roomCategory){
        PersistenceFacade.instance().entityManager.persist(roomCategory.getEntity());
    }

    //update
    public void store(RoomCategory roomCategory){
        PersistenceFacade.instance().entityManager.merge(roomCategory.getEntity());
    }


}
