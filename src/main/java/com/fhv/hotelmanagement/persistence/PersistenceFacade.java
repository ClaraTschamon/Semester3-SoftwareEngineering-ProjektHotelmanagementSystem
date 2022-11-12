package com.fhv.hotelmanagement.persistence;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.dataMapper.BookingDataMapper;
import com.fhv.hotelmanagement.persistence.dataMapper.CustomerDataMapper;
import com.fhv.hotelmanagement.persistence.dataMapper.RoomCategoryDataMapper;
import com.fhv.hotelmanagement.persistence.dataMapper.RoomDataMapper;
import com.fhv.hotelmanagement.persistence.persistenceEntity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
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
    public static Optional<Room> getRoom(int id){
        return RoomDataMapper._instance().get(id);
    }

    public static Optional<Booking> getBooking(int id){
        return BookingDataMapper.instance().get(id);
    }

    public static void main(String[] args) {


        PersistenceFacade pf = new PersistenceFacade();

        Customer clara = new Customer(4, "Clara", "Tschamon", LocalDate.of(2001, Month.JANUARY, 16),
                "Austria", "0664/39422894028", "clara.tsch@gmfai.com",
                "Hummelweg", "36", "6710", "Nenzing", "Austria", true, new ArrayList<>());
        CustomerDataMapper.instance().insert(clara);
        System.out.println(getCustomer(4).get().getFirstName());

        ArrayList<Room> rooms = RoomDataMapper.getAll();
        ArrayList<RoomCategory> categories = RoomCategoryDataMapper.getAll();

        ArrayList<BookedRoomCategory> bookedRoomCategories = new ArrayList<>();
        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();
        Booking booking = new Booking(104, clara, LocalDate.now(), LocalDateTime.now(), LocalDate.now().plusDays(5),
                null, clara.getAddress().getStreet(), clara.getAddress().getHouseNumber(),
                clara.getAddress().getPostalCode(), clara.getAddress().getCity(), clara.getAddress().getCountry(),
                "this is a comment", "Bar", "12435226", LocalDate.now().plusYears(2),
                "123", bookedRoomCategories, bookedRooms);
        bookedRoomCategories.add(new BookedRoomCategory(booking, categories.get(0), BigDecimal.valueOf(50), 2));
        bookedRoomCategories.add(new BookedRoomCategory(booking, categories.get(1), BigDecimal.valueOf(75), 1));
        bookedRooms.add(new BookedRoom(booking, rooms.get(0), booking.getArrivalDate(), booking.getDepartureDate()));
        bookedRooms.add(new BookedRoom(booking, getRoom(25).get(), booking.getArrivalDate(), booking.getDepartureDate()));
        bookedRooms.add(new BookedRoom(booking, getRoom(27).get(), booking.getArrivalDate(), booking.getDepartureDate()));
        booking.setBookedRoomCategories(bookedRoomCategories);
        booking.setBookedRooms(bookedRooms);

        BookingDataMapper.instance().insert(booking);

        System.out.println(getBooking(104).get().getCustomer().getFirstName());
        for (BookedRoomCategory c : getBooking(104).get().getBookedRoomCategories()) {
            System.out.println(c.getRoomCategory().getName());
        }

    }
}
