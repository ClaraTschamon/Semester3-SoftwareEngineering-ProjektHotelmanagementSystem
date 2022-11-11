package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoom;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomEntity;
import java.util.Optional;

public class BookedRoomDataMapper {
    private BookedRoomDataMapper(){}

    private static BookedRoomDataMapper _instance = new BookedRoomDataMapper();

    public static BookedRoomDataMapper instance(){
        return _instance;
    }

    //read
    public Optional<BookedRoom> get(final Booking booking){
        BookedRoomEntity entity = PersistenceFacade.instance().entityManager.find(BookedRoomEntity.class, booking);
        if(entity != null){
            BookedRoom bookedRoom = new BookedRoom(entity);
            return Optional.of(bookedRoom);
        }
        return Optional.empty();
    }

    //create
    public void insert(BookedRoom bookedRoom){
        var entityManager = PersistenceFacade.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(bookedRoom.getEntity());
        entityManager.getTransaction().commit();
    }

    //update
    public void store(BookedRoom bookedRoom){
        PersistenceFacade.instance().entityManager.merge(bookedRoom.getEntity());
    }
}
