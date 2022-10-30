package com.fhv.hotelmanagement.JPAPersistence;

import com.fhv.hotelmanagement.DBModel.Customer;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BrokerCustomer extends BaseBroker<Customer>{

    @Override
    public List<Customer> getAll() {
        EntityManager entityManager = factory.createEntityManager();
        return (List<Customer>) entityManager.createQuery("from customer").getResultList();
    }
}
