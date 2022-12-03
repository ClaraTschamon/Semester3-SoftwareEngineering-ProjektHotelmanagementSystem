//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.dataMapper.*;

import java.util.*;

import java.time.LocalDate;

//Broker == Mapper
public class PersistenceFacade{

    public static ArrayList<Board> getAllBoards() {
        return BoardDataMapper.getAll();
    }

    public static Optional<Booking> getBooking(Long id){
        return BookingDataMapper.instance().get(id);
    }

    public static ArrayList<Booking> getAllBookings(){return BookingDataMapper.getAll();} //neu von Clara

    public static ArrayList<Booking> getCurrentBookings(){return BookingDataMapper.getCurrentBookings();}

    public static ArrayList<Booking> getAllBookingsBetween(LocalDate minDate, LocalDate maxDate){
        return BookingDataMapper.getAllBookingsBetween(minDate, maxDate);
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
        System.out.println(BookingDataMapper.getCurrentBookings());

        LocalDate minDate = LocalDate.now();
        LocalDate maxDate = LocalDate.now().plusDays(2);

        System.out.println(BookingDataMapper.getAllBookingsBetween(minDate, maxDate));
    }
}
