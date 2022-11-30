//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoom;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class BookedRoomDataMapper {
    private BookedRoomDataMapper(){}

    private static BookedRoomDataMapper _instance = new BookedRoomDataMapper();

    public static BookedRoomDataMapper instance(){
        return _instance;
    }

    //read
    protected Optional<BookedRoom> get(final Booking booking){
        BookedRoomEntity entity = PersistenceManager.instance().entityManager.find(BookedRoomEntity.class, booking);
        if(entity != null){
            BookedRoom bookedRoom = createBookedRoom(entity, booking);
            return Optional.of(bookedRoom);
        }
        return Optional.empty();
    }

    public static ArrayList<BookedRoom> getAll(){
        ArrayList<BookedRoomEntity> entities = (ArrayList<BookedRoomEntity>) PersistenceManager.instance().entityManager.createQuery("from BookedRoomEntity").getResultList();
        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();
        for(BookedRoomEntity e : entities){
            bookedRooms.add(createBookedRoom(e, BookingDataMapper.createBooking(e.getBooking())));
        }
        return bookedRooms;
    }


    public static ArrayList<BookedRoom> getBookedRoomsBetween(LocalDate minDate, LocalDate maxDate){
        ArrayList<BookedRoomEntity> entities;

        entities = (ArrayList<BookedRoomEntity>) PersistenceManager.instance().entityManager.createQuery(
                        "SELECT bookedRoom FROM BookedRoomEntity bookedRoom " +
                                "WHERE (bookedRoom.fromDate < :minimumDate AND :minimumDate <= bookedRoom.toDate) " +
                                "OR (:minimumDate <= bookedRoom.fromDate AND bookedRoom.fromDate <= :maximumDate)")
                .setParameter("minimumDate", minDate)
                .setParameter("maximumDate", maxDate).getResultList();

        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();
        for(BookedRoomEntity e : entities){

            bookedRooms.add(createBookedRoom(e, BookingDataMapper.createBooking(e.getBooking())));
        }
        return bookedRooms;
    }


    //create
    protected void insert(BookedRoom bookedRoom){
        var entityManager = PersistenceManager.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(createBookedRoomEntity(bookedRoom));
        entityManager.getTransaction().commit();
    }

    //update
    protected void store(BookedRoom bookedRoom){
        var entityManager = PersistenceManager.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.merge(createBookedRoomEntity(bookedRoom));
        entityManager.getTransaction().commit();
    }

    protected static BookedRoomEntity createBookedRoomEntity(BookedRoom bookedRoom) {
        Booking booking = bookedRoom.getBooking();
        return new BookedRoomEntity(BookingDataMapper.createBookingEntity(booking,
                CustomerDataMapper.createCustomerEntity(booking.getCustomer())),
                RoomDataMapper.createRoomEntity(bookedRoom.getRoom()),
                bookedRoom.getFromDate(), bookedRoom.getToDate());
    }

    protected static BookedRoom createBookedRoom(BookedRoomEntity bookedRoomEntity, Booking booking) {
        return new BookedRoom(booking, RoomDataMapper.createRoom(bookedRoomEntity.getRoom()), bookedRoomEntity.getFromDate(),
                bookedRoomEntity.getToDate());
    }
}
