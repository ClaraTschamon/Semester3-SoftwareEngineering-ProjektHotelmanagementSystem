package com.fhv.hotelmanagement.JPAPersistence;

import com.fhv.hotelmanagement.DBModel.Room;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BrokerRoom extends BaseBroker<Room>{
    @Override
    public List<Room> getAll() {
        EntityManager entityManager = factory.createEntityManager();
        return (List<Room>) entityManager.createQuery("from Room").getResultList();
    }
}
