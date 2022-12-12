//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.domainController;

import com.fhv.hotelmanagement.domain.exceptions.*;
import com.fhv.hotelmanagement.domain.factory.*;
import com.fhv.hotelmanagement.view.DTOs.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


public class DomainController {

    public static ArrayList<BoardDTO> getAllBoards() {
        return BoardFactory.getAllBoards();
    }

    public static ArrayList<BookedRoomDTO> getBookedRoomsBetween(LocalDate minDate, LocalDate maxDate) {
        return BookedRoomFactory.getBookedRoomsBetween(minDate, maxDate);
    }

    public static BookingDTO getBooking(Long number) {
        return BookingFactory.getBooking(number);
    }

    public static ArrayList<BookingDTO> getAllBookings(){
        return BookingFactory.getAllBookings();
    }

    public static ArrayList<BookingDTO> getAllBookingsBetween(LocalDate minDate, LocalDate maxDate){
        return BookingFactory.getAllBookingsBetween(minDate, maxDate);
    }

    public static ArrayList<BookingDTO> getCurrentBookings(){
        return BookingFactory.getCurrentBookings();
    }

    public static Long saveBooking(BookingDTO bookingDTO) throws BookingIsInvalidException {
        return BookingFactory.saveBooking(bookingDTO);
    }

    public static ArrayList<CustomerDTO> getSavedCustomers() {
        return CustomerFactory.getSavedCustomers();
    }

    public static Long saveCustomer(CustomerDTO customerDTO) throws CustomerIsInvalidException {
        return CustomerFactory.saveCustomer(customerDTO);
    }

    public static Long saveInvoice(InvoiceDTO invoiceDTO) {
        return InvoiceFactory.saveInvoice(invoiceDTO);
    }

    public static ArrayList<RoomDTO> getAllRooms() {
        return RoomFactory.getAllRooms();
    }

    public static HashMap<String, RoomCategoryDTO> getAllRoomCategories() {
        return RoomCategoryFactory.getAllRoomCategories();
    }

    public static ArrayList<BookedRoomCategoryDTO> getAllBookedRoomCategoriesWithoutBookings(){
        return BookedRoomCategoryFactory.getAllBookedRoomCategoriesWithoutBookings();
    }

    public static ReservationDTO getReservation(Long number){return ReservationFactory.getReservation(number);}

    public static ArrayList<ReservedRoomDTO> getReservedRoomsBetween(LocalDate minDate, LocalDate maxDate) {
        return ReservedRoomFactory.getReservedRoomsBetween(minDate, maxDate);
    }

    public static Long saveReservation(ReservationDTO reservationDTO) throws ReservationIsInvalidException {return ReservationFactory.saveReservation(reservationDTO);}

    public static ArrayList<ReservationDTO> getAllReservations(){return ReservationFactory.getAllReservations();}

}
