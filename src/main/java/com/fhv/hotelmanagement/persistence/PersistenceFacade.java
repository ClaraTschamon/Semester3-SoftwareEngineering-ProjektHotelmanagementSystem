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

    public static ArrayList<Reservation> getAllReservations(){return ReservationDataMapper.getAll();}

    public static Long insertBooking(Booking booking) {
        return BookingDataMapper.instance().insert(booking);
    }

    public static void storeBooking(Booking booking) {
        BookingDataMapper.instance().store(booking);
    }

    public static Optional<Reservation> getReservation(Long id){
        return ReservationDataMapper.instance().get(id);
    }

    public static Long insertReservation(Reservation reservation) {return ReservationDataMapper.instance().insert(reservation);}

    public static void storeReservation(Reservation reservation){ReservationDataMapper.instance().store(reservation);}

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

    public static ArrayList<ReservedRoomCategory> getAllReservedRoomCategories() {
        return ReservedRoomCategoryDataMapper.getAll();
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

    public static ArrayList<ReservedRoom> getAllReservedRooms() {
        return ReservedRoomDataMapper.getAll();
    }

    public static ArrayList<BookedRoom> getBookedRoomsBetween(LocalDate minDate, LocalDate maxDate) {
        return BookedRoomDataMapper.getBookedRoomsBetween(minDate, maxDate);
    }

    public static ArrayList<BookedRoomCategory> getAllBookedRoomCategories(){return BookedRoomCategoryDataMapper.getAll();}

    public static void main(String[] args) {

//        ArrayList<Booking> bookings = getAllBookings();
//
//        for (Booking booking:bookings) {
//            System.out.println(booking);
//        }

        //Testing out reservations

//        ArrayList<Reservation> reservations = getAllReservations();
//
//        for (Reservation reservation:reservations) {
//            System.out.println(reservation);
//        }

        //Testing out reservedRoomCategories

        ArrayList<ReservedRoomCategory> reservedRoomCategories = getAllReservedRoomCategories();

        for (ReservedRoomCategory reservedRoomCategory:reservedRoomCategories) {
            System.out.println(reservedRoomCategory);
        }

        //Testing out reservedRooms

        ArrayList<ReservedRoom> reservedRooms = getAllReservedRooms();

        for (ReservedRoom reservedRoom:reservedRooms) {
            System.out.println(reservedRoom);
        }




    }
}
