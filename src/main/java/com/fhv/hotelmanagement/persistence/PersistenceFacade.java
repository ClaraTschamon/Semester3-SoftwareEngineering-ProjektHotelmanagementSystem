package com.fhv.hotelmanagement.persistence;

import com.fhv.hotelmanagement.MainApplication;
import com.fhv.hotelmanagement.domain.domainController.DomainManager;
import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.dataMapper.*;
import com.fhv.hotelmanagement.persistence.persistenceEntity.*;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.lang.reflect.Array;
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

    public static ArrayList<Board> getAllBoards() {
        return BoardDataMapper.getAll();
    }

    public static Optional<Booking> getBooking(int id){
        return BookingDataMapper.instance().get(id);
    }

    public static void insertBooking(Booking booking) {
        BookingDataMapper.instance().insert(booking);
    }

    public static void storeBooking(Booking booking) {
        BookingDataMapper.instance().store(booking);
    }

    @SuppressWarnings("rawtypes")
    public static Optional<Customer> getCustomer(int id){
        return CustomerDataMapper.instance().get(id);
    }

    public static void insertCustomer(Customer customer) {
        CustomerDataMapper.instance().insert(customer);
    }

    public static void storeCustomer(Customer customer) {
        CustomerDataMapper.instance().store(customer);
    }

    public static ArrayList<RoomCategory> getAllRoomCategories() {
        return RoomCategoryDataMapper.getAll();
    }

    @SuppressWarnings("rawtypes")
    public static Optional<Room> getRoom(int id){
        return RoomDataMapper.instance().get(id);
    }

    public static ArrayList<Room> getAllRooms() {
        return RoomDataMapper.getAll();
    }

    public static ArrayList<BookedRoom> getAllBookedRooms() {
        return BookedRoomDataMapper.getAll();
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
                "this is a comment", "Bar", "12435226", "02/24",
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

        DomainManager dm = new DomainManager();
        System.out.println(dm.getAllBookedRoomDTOs().size());
    }
}
