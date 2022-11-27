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

//    public static void main(String[] args) {
//    }
}
