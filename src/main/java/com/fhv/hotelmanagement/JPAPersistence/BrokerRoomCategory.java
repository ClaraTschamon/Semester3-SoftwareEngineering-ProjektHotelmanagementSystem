package com.fhv.hotelmanagement.JPAPersistence;

import com.fhv.hotelmanagement.DBModel.RoomCategory;
import jakarta.persistence.EntityManager;

import java.util.List;


public class BrokerRoomCategory extends BaseBroker<RoomCategory> {

    public List<RoomCategory> getAll() {
        EntityManager entityManager = factory.createEntityManager();
        return (List<RoomCategory>) entityManager.createQuery("from room_category").getResultList();
    }
}
