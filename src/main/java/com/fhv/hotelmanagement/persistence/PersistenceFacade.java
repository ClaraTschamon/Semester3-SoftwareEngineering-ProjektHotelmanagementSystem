package com.fhv.hotelmanagement.persistence;

import com.fhv.hotelmanagement.domainModel.Booking;
import com.fhv.hotelmanagement.domainModel.Customer;
import com.fhv.hotelmanagement.domainModel.Room;
import com.fhv.hotelmanagement.persistence.dataMapper.BookingDataMapper;
import com.fhv.hotelmanagement.persistence.dataMapper.CustomerDataMapper;
import com.fhv.hotelmanagement.persistence.dataMapper.RoomDataMapper;
import com.fhv.hotelmanagement.persistence.persistenceEntity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.*;

import java.time.LocalDate;
import java.time.Month;

//Broker == Mapper
public class PersistenceFacade{

    public final EntityManager entityManager;
    private static PersistenceFacade _instance;

    private PersistenceFacade(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelmanagementDB");
        entityManager = emf.createEntityManager();
    }

    public static PersistenceFacade instance(){
        if(_instance == null){
            _instance = new PersistenceFacade();
        }
        return _instance;
    }


    @SuppressWarnings("rawtypes")
    public static Optional<Customer> getCustomer(int id){
        return CustomerDataMapper.instance().get(id);

    }

    @SuppressWarnings("rawtypes")
    public static Optional<Room> getRoom(int number){
        return RoomDataMapper._instance().get(number);
    }

    public static Optional<Booking> getBooking(int id){
        return BookingDataMapper.instance().get(4);
    }

    public static void main(String[] args) {


        PersistenceFacade pf = new PersistenceFacade();
        System.out.println(getCustomer(1).toString());

        Customer clara = new Customer( new CustomerEntity(4, "Clara", "Tschamon", LocalDate.of(2001, Month.JANUARY, 16),
                "Austria", "0664/39422894028", "clara.tsch@gmfai.com",
                "Hummelweg", "36", "6710", "Nenzing", "Austria", true, null));
        CustomerDataMapper.instance().insert(clara);
        System.out.println(getCustomer(3).get().getFirstName());

        /*
        Booking booking = new Booking(new BookingEntity(3, clara.getEntity(), LocalDate.now(), LocalDateTime.now(), null, null,
                null, null, null, null, null, new LinkedHashSet<>(), new HashSet<>()));
        BookingDataMapper.instance().insert(booking);
        System.out.println(getBooking(4).get().getCustomer().getFirstName());

         */

    }
}
