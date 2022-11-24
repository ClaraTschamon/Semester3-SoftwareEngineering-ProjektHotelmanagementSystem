package com.fhv.hotelmanagement.persistence;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.dataMapper.*;
import com.fhv.hotelmanagement.persistence.persistenceEntity.*;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
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

    public static Optional<Booking> getBooking(Long id){
        return BookingDataMapper.instance().get(id);
    }

    public static Long insertBooking(Booking booking) {
        return BookingDataMapper.instance().insert(booking);
    }

    public static void storeBooking(Booking booking) {
        BookingDataMapper.instance().store(booking);
    }

    @SuppressWarnings("rawtypes")
    public static Optional<Customer> getCustomer(Long id){
        return CustomerDataMapper.instance().get(id);
    }

    public static ArrayList<Customer> getSavedCustomers(){ return CustomerDataMapper.getSavedCustomers();}

    public static Long insertCustomer(Customer customer) {
        return CustomerDataMapper.instance().insert(customer);
    }

    public static void storeCustomer(Customer customer) {
        CustomerDataMapper.instance().store(customer);
    }

    public static Long insertInvoice(Invoice invoice) { return InvoiceDataMapper.instance().insert(invoice); }
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

    public static ArrayList<BookedRoom> getBookedRoomsBetween(LocalDate minDate, LocalDate maxDate) {
        return BookedRoomDataMapper.getBookedRoomsBetween(minDate, maxDate);
    }

    public static void main(String[] args) {

        /*
        Customer clara = new Customer("Clara", "Tschamon", LocalDate.of(2001, Month.JANUARY, 16),
                "Austria", "0664/39422894028", "clara.tsch@gmfai.com",
                "Hummelweg", "36", "6710", "Nenzing", "Austria", true, new ArrayList<>());
        CustomerDataMapper.instance().insert(clara);
        */

//        ArrayList<Room> rooms = RoomDataMapper.getAll();
//        ArrayList<RoomCategory> categories = RoomCategoryDataMapper.getAll();
//
//        ArrayList<BookedRoomCategory> bookedRoomCategories = new ArrayList<>();
//        Board board = new Board("Vollpension", new BigDecimal(30));
//        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();
//        Booking booking = new Booking(clara, LocalDate.now(), LocalDateTime.now(), LocalDate.now().plusDays(5),
//                null, clara.getAddress().getStreet(), clara.getAddress().getHouseNumber(),
//                clara.getAddress().getPostalCode(), clara.getAddress().getCity(), clara.getAddress().getCountry(),
//                "this is a comment", "Bar", "12435226", "02/24",
//                "123", board, new BigDecimal(30), bookedRoomCategories, bookedRooms);
//        BookingDataMapper.instance().insert(booking);
//
//        System.out.println(getCustomer(10).get().getFirstName());

        /*
        PersistenceFacade pf = new PersistenceFacade();

        Customer clara = new Customer(null, "Clara", "Tschamon", LocalDate.of(2001, Month.JANUARY, 16),
                "Austria", "0664/39422894028", "clara.tsch@gmfai.com",
                "Hummelweg", "36", "6710", "Nenzing", "Austria", true, new ArrayList<>());
        CustomerDataMapper.instance().insert(clara);

        System.out.println(getCustomer(4).get().getFirstName());

        ArrayList<Room> rooms = RoomDataMapper.getAll();
        ArrayList<RoomCategory> categories = RoomCategoryDataMapper.getAll();

        ArrayList<BookedRoomCategory> bookedRoomCategories = new ArrayList<>();
        Board board = new Board("Vollpension", new BigDecimal(30));


        ArrayList<BookedRoom> bookedRooms = new ArrayList<>();
        Booking booking = new Booking(106L, clara, LocalDate.now(), LocalDateTime.now(), LocalDate.now().plusDays(5),
                null, clara.getAddress().getStreet(), clara.getAddress().getHouseNumber(),
                clara.getAddress().getPostalCode(), clara.getAddress().getCity(), clara.getAddress().getCountry(),
                "this is a comment", "Bar", "12435226", "02/24",
                "123", board, new BigDecimal(30), bookedRoomCategories, bookedRooms);
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

        //hartkodiert customer und statt nummer einfach null zum ausprobieren von
        */

//        LocalDate minDate = LocalDate.of(2022, 11, 12);
//        LocalDate maxDate = LocalDate.now();
//        ArrayList<BookedRoom> bookedRooms = BookedRoomDataMapper.getBookedRoomsBetween(minDate, maxDate);

//        Customer customer = new Customer("Ida","Mazinger",LocalDate.now().minusYears(15),
//                "österreich","1234","asd@at.at","asdf","asdf",
//                "asdf","asdf","asdf",true,new ArrayList<>());
//        Long n = insertCustomer(customer);
//        System.out.println(n +" "+ getCustomer(n).get().getFirstName());
//        customer.setNumber(n);
//        Booking booking = new Booking(customer,LocalDate.now(),LocalDateTime.now(),LocalDate.now().plusDays(5),
//                null,"a","a","a","a","a",
//                "a","a","a","a","a",null,
//                null,2, new ArrayList<>(),new ArrayList<>());
//        insertBooking(booking);
//        booking.setCheckOutDatetime(LocalDateTime.now().plusDays(5));
//        storeBooking(booking);

        ArrayList<Customer> customers = getSavedCustomers();
        for(Customer c : customers){
            System.out.println(c.toString());
        }
    }
}
