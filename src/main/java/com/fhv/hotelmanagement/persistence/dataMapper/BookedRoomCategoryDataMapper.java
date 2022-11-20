package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoomCategory;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BoardEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomCategoryEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookingEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.CustomerEntity;

import java.util.Optional;

public class BookedRoomCategoryDataMapper{

    private BookedRoomCategoryDataMapper(){}

    private static BookedRoomCategoryDataMapper _instance = new BookedRoomCategoryDataMapper();

    public static BookedRoomCategoryDataMapper instance(){
        return _instance;
    }

    //read
    protected Optional<BookedRoomCategory> get(final Booking booking){
        BookedRoomCategoryEntity entity = PersistenceFacade.instance().entityManager.find(BookedRoomCategoryEntity.class, booking);
        if(entity != null){
            BookedRoomCategory bookedRoomCategory = createBookedRoomCategory(entity, booking);
            return Optional.of(bookedRoomCategory);
        }
        return Optional.empty();
    }

    //create
    public void insert(BookedRoomCategory bookedRoomCategory){ //Autocommit funktioniert nicht
        var entityManager = PersistenceFacade.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(createBookedRoomCategoryEntity(bookedRoomCategory));
        entityManager.getTransaction().commit();
    }

    //update
    protected void store(BookedRoomCategoryEntity bookedRoomCategoryEntity){
        PersistenceFacade.instance().entityManager.merge(bookedRoomCategoryEntity);
    }

    protected static BookedRoomCategoryEntity createBookedRoomCategoryEntity(BookedRoomCategory bookedRoomCategory) {
        Booking booking = bookedRoomCategory.getBooking();
        return new BookedRoomCategoryEntity(BookingDataMapper.createBookingEntity(booking,
                CustomerDataMapper.createCustomerEntity(booking.getCustomer())),
                RoomCategoryDataMapper.createRoomCategoryEntity(bookedRoomCategory.getRoomCategory()),
                bookedRoomCategory.getPricePerNight(), bookedRoomCategory.getAmount());
    }

    protected static BookedRoomCategory createBookedRoomCategory(BookedRoomCategoryEntity bookedRoomCategoryEntity, Booking booking) {
        return new BookedRoomCategory(booking, RoomCategoryDataMapper.createRoomCategory(bookedRoomCategoryEntity.getRoomCategory()),
                bookedRoomCategoryEntity.getPricePerNight(), bookedRoomCategoryEntity.getAmount());
    }
}
