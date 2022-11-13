package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoom;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.domain.domainModel.Room;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookingEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.h2.engine.Session;

import java.sql.*;
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
        BookedRoomEntity entity = PersistenceFacade.instance().entityManager.find(BookedRoomEntity.class, booking);
        if(entity != null){
            BookedRoom bookedRoom = createBookedRoom(entity, booking);
            return Optional.of(bookedRoom);
        }
        return Optional.empty();
    }

    public static ArrayList<BookedRoom> getAll(){
        ArrayList<BookedRoomEntity> entities = (ArrayList<BookedRoomEntity>) PersistenceFacade.instance().entityManager.createQuery("from BookedRoomEntity").getResultList();
        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();
        for(BookedRoomEntity e : entities){
            bookedRooms.add(createBookedRoom(e, BookingDataMapper.createBooking(e.getBooking())));
        }
        return bookedRooms;
    }


    /*
    public ArrayList<BookedRoom> getBookedRoomsBetween(LocalDate minDate, LocalDate maxDate) throws SQLException {
        String minimumDate = minDate.toString();
        String maximumDate = maxDate.toString();
        //Date minimumDate = Date.valueOf(minDate); //Convert from LocalDate to java.sql.Date
        //Date maximumDate = Date.valueOf(maxDate);
        ArrayList<BookedRoomEntity> entities = new ArrayList<>();

        PersistenceFacade.instance().entityManager.getTransaction().begin();


        entities = (ArrayList<BookedRoomEntity>) PersistenceFacade.instance().entityManager.createQuery(
                "SELECT bookedRoom FROM BookedRoomEntity bookedRoom WHERE bookedRoom.fromDate >=: minDate " +
                        "AND bookedRoom.toDate <=: maxDate").setParameter("minDate", minDate
        ).setParameter("maxDate", maxDate).getResultList();





        PersistenceFacade.instance().entityManager.getTransaction().commit();

        
        System.out.println(entities);

        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();
        for(BookedRoomEntity e : entities){

            bookedRooms.add(createBookedRoom(e, BookingDataMapper.createBooking(e.getBooking())));
        }
        return bookedRooms;
       
    }
    */



    //create
    protected void insert(BookedRoom bookedRoom){
        var entityManager = PersistenceFacade.instance().entityManager;
        entityManager.getTransaction().begin();
        entityManager.persist(createBookedRoomEntity(bookedRoom));
        entityManager.getTransaction().commit();
    }

    //update
    protected void store(BookedRoom bookedRoom){
        PersistenceFacade.instance().entityManager.merge(createBookedRoomEntity(bookedRoom));
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
