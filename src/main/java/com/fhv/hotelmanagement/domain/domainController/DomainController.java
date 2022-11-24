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

    public static Long saveBooking(BookingDTO bookingDTO) throws BookingIsInvalidException {
        return BookingFactory.saveBooking(bookingDTO);
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
}
