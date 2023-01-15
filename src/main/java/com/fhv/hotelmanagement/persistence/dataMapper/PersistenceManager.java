//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.dataMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.h2.engine.Session;

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
