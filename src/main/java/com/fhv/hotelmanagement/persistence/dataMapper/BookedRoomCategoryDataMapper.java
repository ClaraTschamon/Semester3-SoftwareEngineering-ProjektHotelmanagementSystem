package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domainModel.BookedRoomCategory;
import com.fhv.hotelmanagement.domainModel.Booking;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomCategoryEntity;
import java.util.Optional;

public class BookedRoomCategoryDataMapper{

    private BookedRoomCategoryDataMapper(){}

    private static BookedRoomCategoryDataMapper _instance = new BookedRoomCategoryDataMapper();

    public static BookedRoomCategoryDataMapper instance(){
        return _instance;
    }

    //read
    public Optional<BookedRoomCategory> get(final Booking booking){
        BookedRoomCategoryEntity entity = PersistenceFacade.instance().entityManager.find(BookedRoomCategoryEntity.class, booking);
        if(entity != null){
            BookedRoomCategory bookedRoomCategory = new BookedRoomCategory(entity);
            return Optional.of(bookedRoomCategory);
        }
        return Optional.empty();
    }

    //create
    public void insert(BookedRoomCategory bookedRoomCategory){
        PersistenceFacade.instance().entityManager.persist(bookedRoomCategory.getEntity());
    }

    //update
    public void store(BookedRoomCategory bookedRoomCategory){
        PersistenceFacade.instance().entityManager.merge(bookedRoomCategory.getEntity());
    }
}
