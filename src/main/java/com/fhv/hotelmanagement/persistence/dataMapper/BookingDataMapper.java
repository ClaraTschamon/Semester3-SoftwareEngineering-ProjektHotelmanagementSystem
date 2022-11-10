package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domainModel.Booking;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookingEntity;
import java.util.Optional;

public class BookingDataMapper {
    private BookingDataMapper(){}

    private static BookingDataMapper _instance = new BookingDataMapper();

    public static BookingDataMapper instance(){
        return _instance;
    }

    //read
    public Optional<Booking> get(final int number){
        BookingEntity entity = PersistenceFacade.instance().entityManager.find(BookingEntity.class, number);
        if(entity != null){
            Booking booking = new Booking(entity);
            return Optional.of(booking);
        }
        return Optional.empty();
    }

    //create
    public void insert(Booking booking){
        var entityManager = PersistenceFacade.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(booking.getEntity());
        entityManager.getTransaction().commit();
    }

    //update
    public void store(Booking booking){
        PersistenceFacade.instance().entityManager.merge(booking.getEntity());
    }

}
