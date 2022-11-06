package com.fhv.hotelmanagement.persistence;

import com.fhv.hotelmanagement.domainModel.Customer;
import com.fhv.hotelmanagement.domainModel.Room;
import com.fhv.hotelmanagement.persistence.dataMapper.CustomerDataMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Optional;

//Broker == Mapper
public class PersistenceFacade{

    public final EntityManager entityManager;
    private static PersistenceFacade _instance;

    private PersistenceFacade(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelmanagementDB");
        entityManager = emf.createEntityManager();
    }

    public static PersistenceFacade instance(){
        if(_instance == null){
            _instance = new PersistenceFacade();
        }
        return _instance;
    }


    @SuppressWarnings("rawtypes")
    public Optional<Customer> getCustomer(int id){
        return CustomerDataMapper.instance().get(id);

    }

    public static void main(String[] args) {
        PersistenceFacade pf = new PersistenceFacade();
        System.out.println(pf.getCustomer(1).toString());

    }
}
