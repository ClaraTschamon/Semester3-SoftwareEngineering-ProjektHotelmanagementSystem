package com.fhv.hotelmanagement.persistence;

import com.fhv.hotelmanagement.domain.domainModel.BookedRoom;
import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.domain.domainModel.Customer;
import com.fhv.hotelmanagement.domain.domainModel.Room;
import com.fhv.hotelmanagement.persistence.dataMapper.BookedRoomDataMapper;
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

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

//Broker == Mapper
public class PersistenceFacade{

    //public static final Logger log4j2Logger = LogManager.getLogger(PersistenceFacade.class);

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

        /*
        Customer clara = new Customer( new CustomerEntity(4, "Clara", "Tschamon", LocalDate.of(2001, Month.JANUARY, 16),
                "Austria", "0664/39422894028", "clara.tsch@gmfai.com",
                "Hummelweg", "36", "6710", "Nenzing", "Austria", true, null));
        CustomerDataMapper.instance().insert(clara);
        //System.out.println(getCustomer(3).get().getFirstName());
        */

        Optional<Customer> optional = CustomerDataMapper.instance().get(4);

        CustomerEntity clara = optional.get().getEntity();

        /*
        Booking booking = new Booking(new BookingEntity(3, clara, LocalDate.now(), LocalDateTime.now(), null, null,
                null, null, null, null, null, new LinkedHashSet<>(), new HashSet<>()));
        BookingDataMapper.instance().insert(booking);
        System.out.println(getBooking(5).get().getCustomer().getFirstName());

         */

        /*

        LocalDate minDate = LocalDate.of(2022, 11, 10);
        LocalDate maxDate = LocalDate.of(2022, 11, 12);


        ArrayList<BookedRoom> rooms = BookedRoomDataMapper.instance().getBookedRoomsBetween(minDate, maxDate);

        System.out.println(rooms.size());
        for(BookedRoom room : rooms){

            System.out.println(room);
        }

         */
    }
}
