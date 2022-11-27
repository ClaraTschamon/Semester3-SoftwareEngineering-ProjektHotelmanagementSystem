package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager {
    protected final EntityManager entityManager;
    private static PersistenceManager _instance;

    protected PersistenceManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelmanagementDB");
        entityManager = emf.createEntityManager();
    }

    protected static PersistenceManager instance(){
        if(_instance == null){
            _instance = new PersistenceManager();
        }
        return _instance;
    }
}
