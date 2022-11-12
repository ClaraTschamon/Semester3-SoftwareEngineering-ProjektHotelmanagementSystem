package com.fhv.hotelmanagement.persistence.dataMapper;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoom;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.domain.domainModel.Room;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.persistence.persistenceEntity.BookedRoomEntity;
import com.fhv.hotelmanagement.persistence.persistenceEntity.RoomEntity;
import jakarta.persistence.TemporalType;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

    //sollte protected sein
    public ArrayList<BookedRoom> getBookedRoomsBetween(LocalDate minDate, LocalDate maxDate){
        ArrayList<BookedRoomEntity> entities;
        entities = (ArrayList<BookedRoomEntity>) PersistenceFacade.instance().entityManager.createQuery(
                "SELECT bookedRoom FROM BookedRoomEntity bookedRoom WHERE bookedRoom.fromDate >=:minDate AND bookedRoom.toDate <=: maxDate").setParameter("minDate", minDate).setParameter("maxDate", maxDate).getResultList();
        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();
        for(BookedRoomEntity e : entities){
            bookedRooms.add(new BookedRoom(e));
        }
        return bookedRooms;
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
