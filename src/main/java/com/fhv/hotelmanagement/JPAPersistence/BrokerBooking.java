package com.fhv.hotelmanagement.JPAPersistence;

import com.fhv.hotelmanagement.DBModel.Booking;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BrokerBooking extends BaseBroker<Booking>{

    @Override
    public List<Booking> getAll() {
        EntityManager entityManager = factory.createEntityManager();
        return (List<Booking>) entityManager.createQuery("from Booking").getResultList();
    }
}
