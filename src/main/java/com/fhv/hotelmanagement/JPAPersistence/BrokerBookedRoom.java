package com.fhv.hotelmanagement.JPAPersistence;

import com.fhv.hotelmanagement.DBModel.BookedRoom;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BrokerBookedRoom extends BaseBroker<BookedRoom>{
    @Override
    public List<BookedRoom> getAll() {
        EntityManager entityManager = factory.createEntityManager();
        return (List<BookedRoom>) entityManager.createQuery("from BookedRoom").getResultList();
    }
}
