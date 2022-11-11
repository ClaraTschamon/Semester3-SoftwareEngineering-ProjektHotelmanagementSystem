package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.RoomCategory;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomCategoryEntity;

import java.util.ArrayList;
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

    //TODO: generisch in persistence facade
    public static ArrayList<RoomCategory> getAll(){
        ArrayList<RoomCategoryEntity> entities = (ArrayList<RoomCategoryEntity>) PersistenceFacade.instance().entityManager.createQuery("from RoomCategoryEntity").getResultList();
        ArrayList<RoomCategory> roomCategories = new ArrayList<>();
        for(RoomCategoryEntity e : entities){
            roomCategories.add(new RoomCategory(e));
        }
        return roomCategories;
    }

    //create
    public void insert(RoomCategory roomCategory){
        var entityManager = PersistenceFacade.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(roomCategory.getEntity());
        entityManager.getTransaction().commit();
    }

    //update
    public void store(RoomCategory roomCategory){
        PersistenceFacade.instance().entityManager.merge(roomCategory.getEntity());
    }


}
