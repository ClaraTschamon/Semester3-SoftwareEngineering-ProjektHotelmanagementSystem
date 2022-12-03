//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoom;
import com.fhv.hotelmanagement.domain.domainModel.BookedRoomCategory;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomCategoryEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomEntity;

import java.util.ArrayList;
import java.util.Optional;

public class BookedRoomCategoryDataMapper{

    private BookedRoomCategoryDataMapper(){}

    private static BookedRoomCategoryDataMapper _instance = new BookedRoomCategoryDataMapper();

    public static BookedRoomCategoryDataMapper instance(){
        return _instance;
    }

    //read
    protected Optional<BookedRoomCategory> get(final Booking booking){
        BookedRoomCategoryEntity entity = PersistenceManager.instance().entityManager.find(BookedRoomCategoryEntity.class, booking);
        if(entity != null){
            BookedRoomCategory bookedRoomCategory = createBookedRoomCategory(entity, booking);
            return Optional.of(bookedRoomCategory);
        }
        return Optional.empty();
    }

    public static ArrayList<BookedRoomCategory> getAll(){
        ArrayList<BookedRoomCategoryEntity> entities = (ArrayList<BookedRoomCategoryEntity>) PersistenceManager.instance().entityManager.createQuery("from BookedRoomCategoryEntity").getResultList();
        ArrayList<BookedRoomCategory> bookedRoomCategories = new ArrayList<>();
        for(BookedRoomCategoryEntity e : entities){
            bookedRoomCategories.add(createBookedRoomCategory(e, BookingDataMapper.createBooking(e.getBooking())));
        }
        return bookedRoomCategories;
    }

    //create
    public void insert(BookedRoomCategory bookedRoomCategory){ //Autocommit funktioniert nicht
        var entityManager = PersistenceManager.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(createBookedRoomCategoryEntity(bookedRoomCategory));
        entityManager.getTransaction().commit();
    }

    //update
    protected void store(BookedRoomCategory bookedRoomCategory){
        var entityManager = PersistenceManager.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.merge(createBookedRoomCategoryEntity(bookedRoomCategory));
        entityManager.getTransaction().commit();
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
