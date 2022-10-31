package com.fhv.hotelmanagement.JPAPersistence;

import com.fhv.hotelmanagement.DBModel.BookedRoomCategory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BrokerBookedRoomCategory extends BaseBroker<BookedRoomCategory>{

    @Override
    public List<BookedRoomCategory> getAll() {
        EntityManager entityManager = factory.createEntityManager();
        return (List<BookedRoomCategory>) entityManager.createQuery("from BookedRoomCategory").getResultList();
    }
}
