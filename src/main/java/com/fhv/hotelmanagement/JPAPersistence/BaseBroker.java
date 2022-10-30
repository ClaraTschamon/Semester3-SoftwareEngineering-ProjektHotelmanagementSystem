package com.fhv.hotelmanagement.JPAPersistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public abstract class BaseBroker<T> {
    protected static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Hotelmanagement");

    public abstract List<T> getAll();

    public void insert(T value) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(value);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(T value) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(value);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(T value) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(value);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
