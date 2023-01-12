//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.persistence;

import com.fhv.hotelmanagement.domain.domainModel.*;
import com.fhv.hotelmanagement.persistence.dataMapper.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import java.time.LocalDate;

//Broker == Mapper
public class PersistenceFacade{

    private static final Logger logger = LogManager.getLogger();

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
        long number = BookingDataMapper.instance().insert(booking);
        logger.info("Booking with number " + number + " was inserted.");
        return number;
    }

    public static void storeBooking(Booking booking) {
        logger.info("Booking with number " + booking.getNumber() + " was stored.");
        BookingDataMapper.instance().store(booking);
    }

    public static Optional<Reservation> getReservation(Long id){
        return ReservationDataMapper.instance().get(id);
    }

    public static ArrayList<Reservation> getAllReservations(){return ReservationDataMapper.getAll();}

    public static ArrayList<Reservation> getAllReservationsBetween(LocalDate minDate, LocalDate maxDate) {
        return ReservationDataMapper.getAllReservationsBetween(minDate, maxDate);
    }

    public static ArrayList<Reservation> getNotConfirmedReservations(){return ReservationDataMapper.getNotConfirmedReservations();}

    public static ArrayList<Reservation> getConfirmedReservations(){return ReservationDataMapper.getConfirmedReservations();}

    public static Long insertReservation(Reservation reservation) {
        long number = ReservationDataMapper.instance().insert(reservation);
        logger.info("Reservation with number" + number + " was inserted.");
        return number;
    }

    public static void storeReservation(Reservation reservation){
        logger.info("Reservation with number " + reservation.getNumber() + " was stored.");
        ReservationDataMapper.instance().store(reservation);
    }

    public static void deleteReservation(Reservation reservation){ReservationDataMapper.instance().delete(reservation);}

    @SuppressWarnings("rawtypes")
    public static Optional<Customer> getCustomer(Long id){
        return CustomerDataMapper.instance().get(id);
    }

    public static ArrayList<Customer> getSavedCustomers(){ return CustomerDataMapper.getSavedCustomers();}

    public static Long insertCustomer(Customer customer) {
        long number = CustomerDataMapper.instance().insert(customer);
        logger.info("Customer with number " + number + " was inserted.");
        return number;
    }

    public static void storeCustomer(Customer customer) {
        logger.info("Customer with number " + customer.getNumber() + " was stored.");
        CustomerDataMapper.instance().store(customer);
    }

    public static Long insertInvoice(Invoice invoice) {
        long number = InvoiceDataMapper.instance().insert(invoice);
        logger.info("Invoice with number " + invoice.getNumber() + " was created.");
        return  number;
    }
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

    public static ArrayList<ReservedRoom> getReservedRoomsBetween(LocalDate minDate, LocalDate maxDate){
        return ReservedRoomDataMapper.getReservedRoomsBetween(minDate, maxDate);
    }

    public static ArrayList<BookedRoom> getBookedRoomsBetween(LocalDate minDate, LocalDate maxDate) {
        return BookedRoomDataMapper.getBookedRoomsBetween(minDate, maxDate);
    }

    public static ArrayList<BookedRoomCategory> getAllBookedRoomCategories(){return BookedRoomCategoryDataMapper.getAll();}

    public static void main(String[] args) {

        /*ArrayList<Reservation> allReservations = ReservationDataMapper.getAll();
        for(Reservation r : allReservations){
            System.out.println(r.getNumber() + " " + r.getBooking());
        }

        System.out.println("\n");
        System.out.println("ReservationsWithBooking");
        ArrayList<Reservation> reservationsWithBooking = ReservationDataMapper.getConfirmedReservations();
        for(Reservation r : reservationsWithBooking){
            System.out.println(r.getNumber() + " " + r.getBooking());
        }

        System.out.println("\n");
        System.out.println("ReservationsWithoutBooking");
        ArrayList<Reservation> reservationsWithoutBooking = ReservationDataMapper.getNotConfirmedReservations();
        for(Reservation r : reservationsWithoutBooking){
            System.out.println(r.getNumber() + " " + r.getBooking());
        }*/
    }
}
